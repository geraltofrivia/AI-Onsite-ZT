// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/us/shandian/giga/get/DownloadMission.java#L792-L830
public class TempClass {
    private void joinForThreads(int millis) {
        final Thread currentThread = Thread.currentThread();

        if (init != null && init != currentThread && init.isAlive()) {
            init.interrupt();

            if (millis > 0) {
                try {
                    init.join(millis);
                } catch (InterruptedException e) {
                    Log.w(TAG, "Initializer thread is still running", e);
                    return;
                }
            }
        }

        // if a thread is still alive, possible reasons:
        //      slow device
        //      the user is spamming start/pause buttons
        //      start() method called quickly after pause()

        for (Thread thread : threads) {
            if (!thread.isAlive() || thread == Thread.currentThread()) continue;
            thread.interrupt();
        }

        try {
            for (Thread thread : threads) {
                if (!thread.isAlive()) continue;
                if (DEBUG) {
                    Log.w(TAG, "thread alive: " + thread.getName());
                }
                if (millis > 0) thread.join(millis);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("A download thread is still running", e);
        }
    }


}