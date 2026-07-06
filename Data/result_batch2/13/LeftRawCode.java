// https://github.com/zfile-dev/zfile/tree/5c5d2be0cb36898dcabe0828d7eb5825dc539ade/src/main/java/im/zhaojun/zfile/core/util/CharSequenceUtil.java#L226-L247
public class TempClass {
    public static boolean equals(final @Nullable CharSequence cs1, final @Nullable CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        // 逐个比较
        final int length = cs1.length();
        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}