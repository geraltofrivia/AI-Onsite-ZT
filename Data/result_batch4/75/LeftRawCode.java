// https://github.com/JabRef/jabref/tree/6acae662af7a64d25c15dd23dc2f2b5e195be7ec/jablib/src/main/java/org/jabref/model/strings/StringUtil.java#L472-L492
public class TempClass {
    public static int intValueOf(String str) {
        int idx = 0;
        int end;
        boolean sign = false;
        char ch;

        if ((str == null) || ((end = str.length()) == 0) || ((((ch = str.charAt(0)) < '0') || (ch > '9')) && (!(sign = ch == '-') || (++idx == end) || ((ch = str.charAt(idx)) < '0') || (ch > '9')))) {
            throw new NumberFormatException(str);
        }

        int ival = 0;
        for (; ; ival *= 10) {
            ival += '0' - ch;
            if (++idx == end) {
                return sign ? ival : -ival;
            }
            if (((ch = str.charAt(idx)) < '0') || (ch > '9')) {
                throw new NumberFormatException(str);
            }
        }
    }

}