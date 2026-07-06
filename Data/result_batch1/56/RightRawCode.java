// https://github.com/hneemann/Digital/tree/0fc1087e8aef407dffda4846e2b5f1311e9180e2/src/main/java/de/neemann/digital/gui/components/ElementHelpDialog.java#L400-L422
public class TempClass {
    private static String escapeHTML(String text) {
        StringBuilder sb = new StringBuilder(text.length() * 2);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

}