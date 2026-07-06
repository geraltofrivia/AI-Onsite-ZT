// https://github.com/ThirtyDegreesRay/OpenHub/tree/fcb9b422e8013169e2328a6e09c675ce11a27ad6/app/src/main/java/com/thirtydegreesray/openhub/util/HttpUtil.java#L18-L32
public class TempClass {
    @NonNull
    public static Map<String, String> getParams(@NonNull String url){
        Map<String, String> map = new HashMap<>();
        if(!StringUtils.isBlank(url) && url.contains("?")){
            String paramsStr = url.substring(url.indexOf("?") + 1);
            String[] params = paramsStr.split("&");
            for(int i = 0; i < params.length; i++){
                String param = params[i];
                String key = param.split("=")[0];
                String value = param.split("=")[1];
                map.put(key, value);
            }
        }
        return map;
    }

}