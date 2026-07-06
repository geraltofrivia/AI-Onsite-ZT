// https://github.com/halo-dev/halo/tree/395399f078c39a7cd1b4b29509ab95d7ece0a296/api/src/main/java/run/halo/app/extension/index/KeyComparator.java#L23-L57
public class TempClass {
    private int compareStrings(String a, String b) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            char charA = a.charAt(i);
            char charB = b.charAt(j);

            if (Character.isDigit(charA) && Character.isDigit(charB)) {
                // Both characters are digits, compare as numbers
                int compareResult = compareNumbers(a, b, i, j);
                if (compareResult != 0) {
                    return compareResult;
                }

                // Move indices past the compared number segments
                i = moveIndexToNextNonDigit(a, i);
                j = moveIndexToNextNonDigit(b, j);
            } else if (charA == charB) {
                // Characters are the same, continue
                i++;
                j++;
            } else if (Character.isDigit(charA)) {
                // If charA is digit and charB is not, digit comes first
                return -1;
            } else if (Character.isDigit(charB)) {
                // If charB is digit and charA is not, digit comes first
                return 1;
            } else {
                // Both are non-digits, compare directly
                return Character.compare(charA, charB);
            }
        }

        return Integer.compare(a.length(), b.length());
    }

}