// https://github.com/facebook/fresco/tree/febd5f3074ce2adb82fb15e81f2a329554a093c8/fbcore/src/main/java/com/facebook/common/internal/ByteStreams.java#L53-L67
public class TempClass {
  public static long copy(InputStream from, OutputStream to) throws IOException {
    checkNotNull(from);
    checkNotNull(to);
    byte[] buf = new byte[BUF_SIZE];
    long total = 0;
    while (true) {
      int r = from.read(buf);
      if (r == -1) {
        break;
      }
      to.write(buf, 0, r);
      total += r;
    }
    return total;
  }

}