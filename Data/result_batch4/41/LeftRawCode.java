// https://github.com/software-mansion/react-native-svg/tree/be06e84ec4809a8071f18f9824ffbe61424ee80d/android/src/main/java/com/horcrux/svg/PathParser.java#L605-L672
public class TempClass {
  private static float parse_number() {
    // Strip off leading whitespaces.
    skip_spaces();

    if (i == l) {
      throw new Error(String.format("Unexpected end (s=%s)", s));
    }

    int start = i;

    char c = s.charAt(i);

    // Consume sign.
    if (c == '-' || c == '+') {
      i += 1;
      c = s.charAt(i);
    }

    // Consume integer.
    if (c >= '0' && c <= '9') {
      skip_digits();
      if (i < l) {
        c = s.charAt(i);
      }
    } else if (c != '.') {
      throw new IllegalArgumentException(
          String.format("Invalid number formating character '%c' (i=%d, s=%s)", c, i, s));
    }

    // Consume fraction.
    if (c == '.') {
      i += 1;
      skip_digits();
      if (i < l) {
        c = s.charAt(i);
      }
    }

    if ((c == 'e' || c == 'E') && i + 1 < l) {
      char c2 = s.charAt(i + 1);
      // Check for `em`/`ex`.
      if (c2 != 'm' && c2 != 'x') {
        i += 1;
        c = s.charAt(i);

        if (c == '+' || c == '-') {
          i += 1;
          skip_digits();
        } else if (c >= '0' && c <= '9') {
          skip_digits();
        } else {
          throw new IllegalArgumentException(
              String.format("Invalid number formating character '%c' (i=%d, s=%s)", c, i, s));
        }
      }
    }

    String num = s.substring(start, i);
    float n = Float.parseFloat(num);

    // inf, nan, etc. are an error.
    if (Float.isInfinite(n) || Float.isNaN(n)) {
      throw new IllegalArgumentException(
          String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", num, start, i, s));
    }

    return n;
  }

}