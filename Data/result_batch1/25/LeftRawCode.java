// https://github.com/auth0/java-jwt/tree/ee7332b023719a9007be0caf5ef7608840fc4946/lib/src/test/java/com/auth0/jwt/JsonMatcher.java#L111-L124
public class TempClass {
    private String listToString(List<Object> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            sb.append(objectToString(o));
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}