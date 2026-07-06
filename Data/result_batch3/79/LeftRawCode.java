// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/net/lightbody/bmp/BrowserMobProxyServer.java#L414-L432
public class TempClass {
    protected void stop(boolean graceful) {
        if (isStarted()) {
            if (stopped.compareAndSet(false, true)) {
                if (proxyServer != null) {
                    if (graceful) {
                        proxyServer.stop();
                    } else {
                        proxyServer.abort();
                    }
                } else {
                    log.warn("Attempted to stop proxy server, but proxy was never successfully started.");
                }
            } else {
                throw new IllegalStateException("Proxy server is already stopped. Cannot re-stop.");
            }
        } else {
            throw new IllegalStateException("Proxy server has not been started");
        }
    }

}