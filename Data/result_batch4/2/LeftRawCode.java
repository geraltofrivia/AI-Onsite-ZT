// https://github.com/nining377/dolby_beta/tree/258ab9b95671ecfb13a03636842890d9ca36fe75/app/src/main/java/com/raincat/dolby_beta/hook/ProxyHook.java#L64-L80
public class TempClass {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                if (param.args.length == 3) {
                    Object client = param.args[0];
                    Object request = param.args[1];

                    Field urlField = request.getClass().getDeclaredField(fieldHttpUrl);
                    urlField.setAccessible(true);
                    Object urlObj = urlField.get(request);
                    for (String url : whiteUrlList) {
                        if (urlObj.toString().contains(url)) {
                            setProxy(context, client);
                            break;
                        }
                    }
                }
            }

}