// https://github.com/zfile-dev/zfile/tree/5c5d2be0cb36898dcabe0828d7eb5825dc539ade/src/main/java/im/zhaojun/zfile/core/util/CharSequenceUtil.java#L613-L629
public class TempClass {
    public static boolean endWith(final @Nullable CharSequence str, final @Nullable CharSequence suffix, boolean ignoreCase, boolean ignoreEquals) {
        if (null == str || null == suffix) {
            if (ignoreEquals) {
                return false;
            }
            return null == str && null == suffix;
        }

        final int strOffset = str.length() - suffix.length();
        boolean isEndWith = str.toString()
                .regionMatches(ignoreCase, strOffset, suffix.toString(), 0, suffix.length());

        if (isEndWith) {
            return (!ignoreEquals) || (!equals(str, suffix, ignoreCase));
        }
        return false;
    }

}