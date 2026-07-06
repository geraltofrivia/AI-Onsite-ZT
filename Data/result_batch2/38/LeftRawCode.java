// https://github.com/shuzheng/zheng/tree/7005c0a775e6d014d1dc8a8a809f7b1c13bf785a/zheng-common/src/main/java/com/zheng/common/util/StringUtil.java#L23-L39
public class TempClass {
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

}