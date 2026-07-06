// https://github.com/jeequan/jeepay/tree/f53cb2132934823389a8c9de8a4a049f7ab1cfb0/jeepay-core/src/main/java/com/jeequan/jeepay/core/beans/RequestKitBean.java#L144-L165
public class TempClass {
        // 注意2： springMVC的请求模式为线程池，如果采用ThreadLocal保存对象信息，可能会出现不清空或者被覆盖的问题。
        Object reqParamObject = RequestContextHolder.getRequestAttributes().getAttribute(REQ_CONTEXT_KEY_PARAMJSON, RequestAttributes.SCOPE_REQUEST);
        if(reqParamObject == null){
            JSONObject reqParam = reqParam2JSON();
            RequestContextHolder.getRequestAttributes().setAttribute(REQ_CONTEXT_KEY_PARAMJSON, reqParam, RequestAttributes.SCOPE_REQUEST);
            return reqParam;
        }
        return (JSONObject) reqParamObject;
    }

    /** 判断请求参数是否转换为json格式 */
    private boolean isConvertJSON(){

        String contentType = request.getContentType();

        //有contentType  && json格式，  get请求不转换
        if(contentType != null
                && contentType.toLowerCase().indexOf("application/json") >= 0
                && !request.getMethod().equalsIgnoreCase("GET")
        ){ //application/json 需要转换为json格式；
            return true;
        }

}