// https://github.com/java-decompiler/jd-gui/tree/b3c1ced04e571fc316648d114ff0c8121e051d8f/services/src/main/java/org/jd/gui/view/component/AbstractTextPage.java#L365-L386
public class TempClass {
    protected Map<String, String> parseQuery(String query) {
        HashMap<String, String> parameters = new HashMap<>();

        // Parse parameters
        try {
            for (String param : query.split("&")) {
                int index = param.indexOf('=');

                if (index == -1) {
                    parameters.put(URLDecoder.decode(param, "UTF-8"), "");
                } else {
                    String key = param.substring(0, index);
                    String value = param.substring(index + 1);
                    parameters.put(URLDecoder.decode(key, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException e) {
            assert ExceptionUtil.printStackTrace(e);
        }

        return parameters;
    }

}