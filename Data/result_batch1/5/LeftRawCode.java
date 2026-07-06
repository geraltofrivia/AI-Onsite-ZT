// https://github.com/auth0/java-jwt/tree/ee7332b023719a9007be0caf5ef7608840fc4946/lib/src/test/java/com/auth0/jwt/JsonMatcher.java#L79-L95
public class TempClass {
    private String objectToString(Object value) {
        String stringValue;
        if (value == null) {
            stringValue = "null";
        } else if (value instanceof String) {
            stringValue = "\"" + value + "\"";
        } else if (value instanceof Map) {
            stringValue = mapToString((Map<String, Object>) value);
        } else if (value instanceof Array) {
            stringValue = arrayToString((Object[]) value);
        } else if (value instanceof List) {
            stringValue = listToString((List<Object>) value);
        } else {
            stringValue = value.toString();
        }
        return stringValue;
    }

}