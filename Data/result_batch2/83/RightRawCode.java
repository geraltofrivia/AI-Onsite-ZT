// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/player/playback/MediaSourceManager.java#L181-L191
public class TempClass {
    public void dispose() {
        if (DEBUG) {
            Log.d(TAG, "close() called.");
        }

        debouncedSignal.onComplete();
        debouncedLoader.dispose();

        playQueueReactor.cancel();
        loaderReactor.dispose();
    }

}