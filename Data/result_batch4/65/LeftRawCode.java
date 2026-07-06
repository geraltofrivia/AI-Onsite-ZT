// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/searches/BoyerMoore.java#L28-L53
public class TempClass {
    public int search(String text) {
        if (pattern.isEmpty()) {
            return 0;
        }

        int m = pattern.length();
        int n = text.length();

        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                char txtChar = text.charAt(i + j);
                char patChar = pattern.charAt(j);
                if (patChar != txtChar) {
                    skip = Math.max(1, j - right[txtChar]);
                    break;
                }
            }
            if (skip == 0) {
                return i; // Match found
            }
        }

        return -1; // No match
    }

}