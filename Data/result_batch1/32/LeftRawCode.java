// https://github.com/testcontainers/testcontainers-java/tree/768a2af266bf20be4800281053c83d4d1f345942/core/src/main/java/org/testcontainers/utility/ComparableVersion.java#L46-L72
public class TempClass {
    @VisibleForTesting
    static int[] parseVersion(final String version) {
        final List<Integer> parts = new ArrayList<>(5);

        int acc = 0;
        for (final char c : version.toCharArray()) {
            if (c == '.') {
                parts.add(acc);
                acc = 0;
            }

            if (Character.isDigit(c)) {
                acc = 10 * acc + Character.digit(c, 10);
            }
        }

        if (acc != 0) {
            parts.add(acc);
        }

        final int[] ret = new int[parts.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = parts.get(i);
        }

        return ret;
    }

}