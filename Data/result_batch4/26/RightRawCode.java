// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/xposed-bridge/src/main/apacheCommonsLang/external/org/apache/commons/lang3/StringUtils.java#L3553-L3569
public class TempClass {
    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }

}