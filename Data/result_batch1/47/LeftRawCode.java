// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/Util.java#L1047-L1064
public class TempClass {
    @NonNull
    public static String xmlEscape(@NonNull String text) {
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
                buf.append(ch);
        }
        return buf.toString();
    }

}