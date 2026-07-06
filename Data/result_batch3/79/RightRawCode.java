// https://github.com/krlvm/PowerTunnel-Android/tree/c57d90b9863407bebf465750807c97eed7c9d9e7/app/src/main/java/io/github/krlvm/powertunnel/android/managers/ProxyManager.java#L145-L157
public class TempClass {
    public void stop(boolean graceful) {
        if(this.server == null) {
            Log.w(LOG_TAG, "Attempted to stop server when it is not running");
            return;
        }
        if (!this.server.isRunning()) return;

        new Thread(() -> {
            Log.i(LOG_TAG, "Stopping server...");
            this.server.stop(graceful);
            Log.i(LOG_TAG, "Server has been stopped");
        }, "Proxy Shutdown").start();
    }

}