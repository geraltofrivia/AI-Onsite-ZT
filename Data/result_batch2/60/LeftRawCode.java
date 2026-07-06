// https://github.com/ssssssss-team/spider-flow/tree/c799cca99c7d064673dc79e6ef06a08bbc0e292d/spider-flow-core/src/main/java/org/spiderflow/core/executor/shape/RequestExecutor.java#L116-L317
public class TempClass {
	@Override
	public void execute(SpiderNode node, SpiderContext context, Map<String,Object> variables) {
		CookieContext cookieContext = context.getCookieContext();
		String sleepCondition = node.getStringJsonValue(SLEEP);
		if(StringUtils.isNotBlank(sleepCondition)){
			try {
				Object value = ExpressionUtils.execute(sleepCondition, variables);
				if(value != null){
					long sleepTime = NumberUtils.toLong(value.toString(), 0L);
					synchronized (node.getNodeId().intern()) {
						//实际等待时间 = 上次执行时间 + 睡眠时间 - 当前时间
						Long lastExecuteTime = context.get(LAST_EXECUTE_TIME + node.getNodeId(), 0L);
						if (lastExecuteTime != 0) {
							sleepTime = lastExecuteTime + sleepTime - System.currentTimeMillis();
						}
						if (sleepTime > 0) {
							context.pause(node.getNodeId(),"common",SLEEP,sleepTime);
							logger.debug("设置延迟时间:{}ms", sleepTime);
							Thread.sleep(sleepTime);
						}
						context.put(LAST_EXECUTE_TIME + node.getNodeId(), System.currentTimeMillis());
					}
				}
			} catch (Throwable t) {
				logger.error("设置延迟时间失败", t);
			}
		}
		BloomFilter<String> bloomFilter = null;
		//重试次数
		int retryCount = NumberUtils.toInt(node.getStringJsonValue(RETRY_COUNT), 0) + 1;
		//重试间隔时间，单位毫秒
		int retryInterval = NumberUtils.toInt(node.getStringJsonValue(RETRY_INTERVAL), 0);
        boolean successed = false;
		for (int i = 0; i < retryCount && !successed; i++) {
			HttpRequest request = HttpRequest.create();
			//设置请求url
			String url = null;
			try {
				url = ExpressionUtils.execute(node.getStringJsonValue(URL), variables).toString();
			} catch (Exception e) {
				logger.error("设置请求url出错，异常信息", e);
				ExceptionUtils.wrapAndThrow(e);
			}
			if("1".equalsIgnoreCase(node.getStringJsonValue(REPEAT_ENABLE,"0"))){
				bloomFilter = createBloomFilter(context);
				synchronized (bloomFilter){
					if(bloomFilter.mightContain(MD5FunctionExecutor.string(url))){
						logger.info("过滤重复URL:{}",url);
						return;
					}
				}
			}
			context.pause(node.getNodeId(),"common",URL,url);
			logger.info("设置请求url:{}", url);
			request.url(url);
			//设置请求超时时间
			int timeout = NumberUtils.toInt(node.getStringJsonValue(TIMEOUT), 60000);
			logger.debug("设置请求超时时间:{}", timeout);
			request.timeout(timeout);

			String method = Objects.toString(node.getStringJsonValue(REQUEST_METHOD), "GET");
			//设置请求方法
			request.method(method);
			logger.debug("设置请求方法:{}", method);

			//是否跟随重定向
			boolean followRedirects = !"0".equals(node.getStringJsonValue(FOLLOW_REDIRECT));
			request.followRedirect(followRedirects);
			logger.debug("设置跟随重定向：{}", followRedirects);

			//是否验证TLS证书,默认是验证
			if("0".equals(node.getStringJsonValue(TLS_VALIDATE))){
				request.validateTLSCertificates(false);
				logger.debug("设置TLS证书验证：{}", false);
			}
			SpiderNode root = context.getRootNode();
			//设置请求header
			setRequestHeader(root, request, root.getListJsonValue(HEADER_NAME,HEADER_VALUE), context, variables);
			setRequestHeader(node, request, node.getListJsonValue(HEADER_NAME,HEADER_VALUE), context, variables);

			//设置全局Cookie
			Map<String, String> cookies = getRequestCookie(root, root.getListJsonValue(COOKIE_NAME, COOKIE_VALUE), context, variables);
			if(!cookies.isEmpty()){
				logger.info("设置全局Cookie：{}", cookies);
				request.cookies(cookies);
			}
			//设置自动管理的Cookie
			boolean cookieAutoSet = !"0".equals(node.getStringJsonValue(COOKIE_AUTO_SET));
			if(cookieAutoSet && !cookieContext.isEmpty()){
				context.pause(node.getNodeId(),COOKIE_AUTO_SET,COOKIE_AUTO_SET,cookieContext);
				request.cookies(cookieContext);
				logger.info("自动设置Cookie：{}", cookieContext);
			}
			//设置本节点Cookie
			cookies = getRequestCookie(node, node.getListJsonValue(COOKIE_NAME, COOKIE_VALUE), context, variables);
			if(!cookies.isEmpty()){
				request.cookies(cookies);
				logger.debug("设置Cookie：{}", cookies);
			}
			if(cookieAutoSet){
				cookieContext.putAll(cookies);
			}

			String bodyType = node.getStringJsonValue(BODY_TYPE);
			List<InputStream> streams = null;
			if("raw".equals(bodyType)){
				String contentType = node.getStringJsonValue(BODY_CONTENT_TYPE);
				request.contentType(contentType);
				try {
					Object requestBody = ExpressionUtils.execute(node.getStringJsonValue(REQUEST_BODY), variables);
					context.pause(node.getNodeId(),"request-body",REQUEST_BODY,requestBody);
					request.data(requestBody);
					logger.info("设置请求Body:{}", requestBody);
				} catch (Exception e) {
					logger.debug("设置请求Body出错", e);
				}
			}else if("form-data".equals(bodyType)){
				List<Map<String, String>> formParameters = node.getListJsonValue(PARAMETER_FORM_NAME,PARAMETER_FORM_VALUE,PARAMETER_FORM_TYPE,PARAMETER_FORM_FILENAME);
				streams = setRequestFormParameter(node,request,formParameters,context,variables);
			}else{
				//设置请求参数
				setRequestParameter(root, request, root.getListJsonValue(PARAMETER_NAME,PARAMETER_VALUE), context, variables);
				setRequestParameter(node, request, node.getListJsonValue(PARAMETER_NAME,PARAMETER_VALUE), context, variables);
			}
			//设置代理
			String proxy = node.getStringJsonValue(PROXY);
			if(StringUtils.isNotBlank(proxy)){
				try {
					Object value = ExpressionUtils.execute(proxy, variables);
					context.pause(node.getNodeId(),"common",PROXY,value);
					if(value != null){
						String[] proxyArr = value.toString().split(":");
						if(proxyArr.length == 2){
							request.proxy(proxyArr[0], Integer.parseInt(proxyArr[1]));
							logger.info("设置代理：{}",proxy);
						}
					}
				} catch (Exception e) {
					logger.error("设置代理出错，异常信息:{}",e);
				}
			}
			Throwable exception = null;
			try {
				HttpResponse response = request.execute();
                successed = response.getStatusCode() == 200;
                if(successed){
                	if(bloomFilter != null){
                		synchronized (bloomFilter){
							bloomFilter.put(MD5FunctionExecutor.string(url));
						}
					}
                    String charset = node.getStringJsonValue(RESPONSE_CHARSET);
                    if(StringUtils.isNotBlank(charset)){
                        response.setCharset(charset);
                        logger.debug("设置response charset:{}",charset);
                    }
                    //cookie存入cookieContext
                    cookieContext.putAll(response.getCookies());
                    //结果存入变量
                    variables.put("resp", response);
                }
			} catch (IOException e) {
				successed = false;
                exception = e;
			} finally{
                if(streams != null){
                    for (InputStream is : streams) {
                        try {
                            is.close();
                        } catch (Exception e) {
                        }
                    }
                }
                if(!successed){
                    if(i + 1 < retryCount){
                        if(retryInterval > 0){
                            try {
                                Thread.sleep(retryInterval);
                            } catch (InterruptedException ignored) {
                            }
                        }
                        logger.info("第{}次重试:{}",i + 1,url);
                    }else{
                        //记录访问失败的日志
						if(context.getFlowId() != null){ //测试环境
							//TODO 需增加记录请求参数
							File file = new File(workspcace, context.getFlowId() + File.separator + "logs" + File.separator + "access_error.log");
							try {
								File directory = file.getParentFile();
								if(!directory.exists()){
									directory.mkdirs();
								}
								FileUtils.write(file,url + "\r\n","UTF-8",true);
							} catch (IOException ignored) {
							}
						}
                        logger.error("请求{}出错,异常信息:{}",url,exception);
                    }
                }
			}
		}
	}

}