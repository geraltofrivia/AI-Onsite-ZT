// https://github.com/Heeexy/SpringBoot-Shiro-Vue/tree/170e06aec4f118e2cb9ba0ae995f7b341806ddf1/back/src/main/java/com/heeexy/example/config/filter/WebLogAspect.java#L76-L101
public class TempClass {
    private JSONObject getRequestInfo(HttpServletRequest req) {
        JSONObject requestInfo = new JSONObject();
        try {
            StringBuffer requestURL = req.getRequestURL();
            requestInfo.put("requestURL", requestURL);
            String method = req.getMethod();
            requestInfo.put("method", method);
            if (req.getQueryString() != null) {
                requestInfo.put("queryString", URLDecoder.decode(req.getQueryString(), "UTF-8"));
            }
            String remoteAddr = req.getRemoteAddr();
            requestInfo.put("remoteAddr", remoteAddr);
            if (req instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) req;
                String bodyStr = new String(wrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                if (bodyStr.startsWith("{")) {
                    JSONObject jsonObject = JSON.parseObject(bodyStr);
                    requestInfo.put("requestBody", jsonObject);
                }
            }
        } catch (Exception e) {
            log.error("解析请求失败", e);
            requestInfo.put("parseError", e.getMessage());
        }
        return requestInfo;
    }

}