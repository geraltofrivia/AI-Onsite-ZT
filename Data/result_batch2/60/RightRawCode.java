// https://github.com/iSafeBlue/TrackRay/tree/ec6749080132791a72d23892e9d6fb689d32dd1d/base/src/main/java/com/trackray/base/httpclient/HttpClientWrapper.java#L307-L431
public class TempClass {
        if (crawlerPage.getRequest().getUrl() == null)
            return null;

        String url = crawlerPage.getRequest().getUrl();
        /*if (crawlerPage.getRequest().getCharset() != null)
            url = HttpClientWrapper.encodeURL(url.trim(), crawlerPage.getRequest().getCharset());
        */
        URI uri = null;
        if (crawlerPage.getRequest().getCharset() != null) {
            try {
                final String tempURL = escapeURIIfNotAscii(url, crawlerPage.getRequest().getCharset());
                uri = new URL(tempURL).toURI();
                //uri = new URI(escapeURIIfNotAscii(url, crawlerPage.getRequest().getCharset()));
            } catch (Exception e) {
                SysLog.error(e.getMessage());
            }
        }
        HttpEntity entity = null;
        HttpRequestBase request = null;
        CloseableHttpResponse response = null;
        Response resp = new Response();
        ResponseStatus ret = new ResponseStatus();

        try {
            if (HttpMethod.GET == crawlerPage.getRequest().getHttpMethod()) {
                request = new HttpGet((uri));
            } else if (HttpMethod.POST == crawlerPage.getRequest().getHttpMethod()) {
                this.parseUrl(url);
                Map<String, String> paramMap = crawlerPage.getRequest().getParamMap();
                String paramStr = crawlerPage.getRequest().getParamStr();
                HttpPost httpPost = new HttpPost(uri);
                List<NameValuePair> nvBodyList = this.getNVBodies();
                if (StringUtils.isBlank(paramStr) && paramMap!=null && !paramMap.isEmpty())
                {
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        addNV(entry.getKey(),entry.getValue());
                    }
                    httpPost.setEntity(new UrlEncodedFormEntity(nvBodyList, crawlerPage.getRequest().getCharset()));
                }else if (StringUtils.isNotBlank(paramStr)){
                    httpPost.setEntity(new StringEntity(paramStr));
                }
                if (null == (httpPost.getFirstHeader("Content-Type")))
                    httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
                request = httpPost;
            }

            if (crawlerPage.getRequest().getHttpHeaders().length>1) {
                request.setHeaders(crawlerPage.getRequest().getHttpHeaders());
            }else {
                request.addHeader(HttpHeaders.USER_AGENT,
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
            }

            if (StringUtils.isNotBlank(crawlerPage.getRequest().getCookie())){
                request.setHeader(new BasicHeader("Cookie",crawlerPage.getRequest().getCookie()));
            }

            int timeout = crawlerPage.getRequest().getTimeout();
            if (timeout > 1) {
                RequestConfig build = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setRedirectsEnabled(crawlerPage.isRedirect()).build();
                request.setConfig(build);
            }
            response = client.execute(request);

            /*if ((response.getStatusLine().getStatusCode() ==301 || response.getStatusLine().getStatusCode()==302) && crawlerPage.isRedirect() )
            {
                String location = response.getFirstHeader("Location").getValue();
                URI locationURI = new URI(escapeURIIfNotAscii(location, "UTF-8"));
                request.setURI(locationURI);
                response = client.execute(request);
            }*/
            entity = response.getEntity(); // 获取响应实体
            StatusLine statusLine = response.getStatusLine();
            ret.setStatusCode(statusLine.getStatusCode());
            ret.setEncoding(crawlerPage.getRequest().getCharset());
            if (entity!=null){
                getResponseStatus(entity, ret);
            }
            ret.setHeaders(response.getAllHeaders());
            //cookies
            String respCookie = "";
            Header[] cookies = response.getHeaders("Set-Cookie");
            if (cookies.length>0){
                HashMap<String, String> temp = new HashMap<>();
                for (Header cookie : cookies) {
                    String value = cookie.getValue();
                    if (value.contains(";")){
                        String[] split = value.split(";");
                        for (String s : split) {
                            if (s.contains("=")){
                                int index = s.indexOf("=");
                                String k = s.substring(0, index);
                                String v = s.substring(index + 1, s.length());
                                /*String[] sp = s.split("=");
                                String k = sp[0];
                                String v = sp.length>1?sp[1]:"";*/
                                if (k.contains("Max-Age") ||k.contains("expires") || k.contains("path") || k.contains("domain")){
                                    continue;
                                }else {
                                    temp.put(k,v);
                                }
                            }
                        }
                    }
                }
                for (Map.Entry<String, String> e : temp.entrySet()) {
                    respCookie+=e.getKey()+"="+e.getValue()+";";
                }
            }
            ret.setCookie(respCookie);
        } catch (Exception e) {
            SysLog.error(e.getMessage());
        } finally {
            try {
                close(entity, request, response);
            } catch (IOException e) {
                //e.printStackTrace();
            }
            resp.setStatus(ret);
            crawlerPage.setResponse(resp);
        }
        return crawlerPage;
    }
    public static String escapeURIIfNotAscii(final String uri,
                                             final String charset) throws UnsupportedEncodingException {

}