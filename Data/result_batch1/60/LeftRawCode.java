// https://github.com/facebook/stetho/tree/2198797c0ff961dd2d9b87efa711a7d734c0e4d5/stetho/src/main/java/com/facebook/stetho/inspector/network/ResponseHandlingInputStream.java#L127-L141
public class TempClass {
  @Override
  public synchronized long skip(long n) throws IOException {
    byte[] buffer = getSkipBufferLocked();
    long total = 0;
    while (total < n) {
      long bytesDiff = n - total;
      int bytesToRead = (int) Math.min((long) buffer.length, bytesDiff);
      int result = this.read(buffer, 0, bytesToRead);
      if (result == -1) {
        break;
      }
      total += result;
    }
    return total;
  }

}