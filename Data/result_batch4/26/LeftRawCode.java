// https://github.com/microsoft/typespec/tree/2bccd0226a134214538acb2eb6af04a5626858d5/packages/http-client-java/generator/http-client-generator-core/src/main/java/com/microsoft/typespec/http/client/generator/core/util/CodeNamer.java#L225-L254
public class TempClass {
    public static String removeSpaceCharacters(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = null;
        int prevStart = 0;
        int strLength = str.length();

        for (int i = 0; i < strLength; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                if (sb == null) {
                    sb = new StringBuilder(strLength);
                }

                if (prevStart != i) {
                    sb.append(str, prevStart, i);
                }

                prevStart = i + 1;
            }
        }

        if (sb == null) {
            return str;
        }

        sb.append(str, prevStart, strLength);
        return sb.toString();
    }

}