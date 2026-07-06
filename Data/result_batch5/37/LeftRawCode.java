// https://github.com/facebook/stetho/tree/2198797c0ff961dd2d9b87efa711a7d734c0e4d5/stetho/src/main/java/com/facebook/stetho/server/LocalSocketServer.java#L131-L151
public class TempClass {
  @Nonnull
  private static LocalServerSocket bindToSocket(String address) throws IOException {
    int retries = MAX_BIND_RETRIES;
    IOException firstException = null;
    do {
      try {
        if (LogUtil.isLoggable(Log.DEBUG)) {
          LogUtil.d("Trying to bind to @" + address);
        }
        return new LocalServerSocket(address);
      } catch (BindException be) {
        LogUtil.w(be, "Binding error, sleep " + TIME_BETWEEN_BIND_RETRIES_MS + " ms...");
        if (firstException == null) {
          firstException = be;
        }
        Util.sleepUninterruptibly(TIME_BETWEEN_BIND_RETRIES_MS);
      }
    } while (retries-- > 0);

    throw firstException;
  }

}