// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/utils/Utils.java#L658-L678
public class TempClass {
    public static Map<String, String> parseUrlQuery(String query) {
        Map<String, String> res = new HashMap<>();

        if (query.equals("")) {
            return res;
        }

        String[] parts = query.split("&");
        int pos;

        for (String part : parts) {
            if ((pos = part.indexOf('=')) >= 0) {
                res.put(URLDecoder.decode(part.substring(0, pos), StandardCharsets.UTF_8),
                        URLDecoder.decode(part.substring(pos + 1), StandardCharsets.UTF_8));
            } else {
                res.put(URLDecoder.decode(part, StandardCharsets.UTF_8), "");
            }
        }

        return res;
    }

}