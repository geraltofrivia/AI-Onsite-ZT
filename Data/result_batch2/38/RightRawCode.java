// https://github.com/pingfangushi/screw/tree/33fdb4f4821175b82ea6c28fdabd63d6697de059/screw-extension/src/main/java/cn/smallbun/screw/extension/pojo/strategy/HumpNameStrategy.java#L61-L74
public class TempClass {
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母转大写
     *
     * @param str {@link String}
     * @return  {@link String}
     */

}