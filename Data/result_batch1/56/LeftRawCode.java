// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/Functions.java#L895-L917
public class TempClass {
    public static String htmlAttributeEscape(String text) {
        StringBuilder buf = new StringBuilder(text.length() + 64);
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '<')
                buf.append("&lt;");
            else
            if (ch == '>')
                buf.append("&gt;");
            else
            if (ch == '&')
                buf.append("&amp;");
            else
            if (ch == '"')
                buf.append("&quot;");
            else
            if (ch == '\'')
                buf.append("&#39;");
            else
                buf.append(ch);
        }
        return buf.toString();
    }

}