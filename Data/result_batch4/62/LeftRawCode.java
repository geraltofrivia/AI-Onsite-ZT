// https://github.com/facebook/stetho/tree/2198797c0ff961dd2d9b87efa711a7d734c0e4d5/stetho/src/main/java/com/facebook/stetho/dumpapp/Framer.java#L176-L191
public class TempClass {
    @Override
    public long skip(long byteCount) throws IOException {
      long skipped = 0;
      int bufSize = (int)Math.min(byteCount, 2048);
      byte[] buf = new byte[bufSize];
      synchronized (Framer.this) {
        while (skipped < byteCount) {
          int n = read(buf);
          if (n < 0) {
            break;
          }
          skipped += n;
        }
      }
      return skipped;
    }

}