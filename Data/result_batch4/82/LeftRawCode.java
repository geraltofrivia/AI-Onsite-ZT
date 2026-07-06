// https://github.com/TooTallNate/Java-WebSocket/tree/8c5766a293c2dd3e0d035c0e0d70f88f57235fa8/src/main/java/org/java_websocket/server/WebSocketServer.java#L403-L466
public class TempClass {
  public void run() {
    if (!doEnsureSingleThread()) {
      return;
    }
    if (!doSetupSelectorAndServerThread()) {
      return;
    }
    try {
      int shutdownCount = 5;
      int selectTimeout = 0;
      while (!selectorthread.isInterrupted() && shutdownCount != 0) {
        SelectionKey key = null;
        try {
          if (isclosed.get()) {
            selectTimeout = 5;
          }
          int keyCount = selector.select(selectTimeout);
          if (keyCount == 0 && isclosed.get()) {
            shutdownCount--;
          }
          Set<SelectionKey> keys = selector.selectedKeys();
          Iterator<SelectionKey> i = keys.iterator();

          while (i.hasNext()) {
            key = i.next();

            if (!key.isValid()) {
              continue;
            }

            if (key.isAcceptable()) {
              doAccept(key, i);
              continue;
            }

            if (key.isReadable() && !doRead(key, i)) {
              continue;
            }

            if (key.isWritable()) {
              doWrite(key);
            }
          }
          doAdditionalRead();
        } catch (CancelledKeyException e) {
          // an other thread may cancel the key
        } catch (ClosedByInterruptException e) {
          return; // do the same stuff as when InterruptedException is thrown
        } catch (WrappedIOException ex) {
          handleIOException(key, ex.getConnection(), ex.getIOException());
        } catch (IOException ex) {
          handleIOException(key, null, ex);
        } catch (InterruptedException e) {
          // FIXME controlled shutdown (e.g. take care of buffermanagement)
          Thread.currentThread().interrupt();
        }
      }
    } catch (RuntimeException e) {
      // should hopefully never occur
      handleFatal(null, e);
    } finally {
      doServerShutdown();
    }
  }

}