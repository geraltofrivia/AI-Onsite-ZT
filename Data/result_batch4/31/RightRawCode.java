// https://github.com/checkstyle/checkstyle/tree/bd9a465994b5a1bfdb27153ce7bbe9b2f2baf8f2/src/main/java/com/puppycrawl/tools/checkstyle/checks/imports/CustomImportOrderCheck.java#L713-L730
public class TempClass {
    private static int compareImports(String import1, String import2) {
        int result = 0;
        final String separator = "\\.";
        final String[] import1Tokens = import1.split(separator);
        final String[] import2Tokens = import2.split(separator);
        for (int i = 0; i != import1Tokens.length && i != import2Tokens.length; i++) {
            final String import1Token = import1Tokens[i];
            final String import2Token = import2Tokens[i];
            result = import1Token.compareTo(import2Token);
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(import1Tokens.length, import2Tokens.length);
        }
        return result;
    }

}