// https://github.com/airsquared/blobsaver/tree/d95dc309d81cef229fdb8590f2dea72a70360c6b/src/main/java/airsquared/blobsaver/app/Utils.java#L489-L501
public class TempClass {
    static boolean containsIgnoreCase(final String str, final String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        final int len = searchStr.length();
        final int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }

}