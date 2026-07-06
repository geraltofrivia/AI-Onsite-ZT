// https://github.com/lipangit/JiaoZiVideoPlayer/tree/3db479b1a816965336fa5884708037a0a2971c33/app/src/main/java/cn/jzvd/demo/CustomMedia/JZMediaExo.java#L211-L236
public class TempClass {
    @Override
    public void onPlayerStateChanged(final boolean playWhenReady, final int playbackState) {
        Log.e(TAG, "onPlayerStateChanged" + playbackState + "/ready=" + String.valueOf(playWhenReady));
        handler.post(() -> {
            switch (playbackState) {
                case Player.STATE_IDLE: {
                }
                break;
                case Player.STATE_BUFFERING: {
                    handler.post(callback);
                }
                break;
                case Player.STATE_READY: {
                    if (playWhenReady) {
                        jzvd.onStatePlaying();
                    } else {
                    }
                }
                break;
                case Player.STATE_ENDED: {
                    jzvd.onAutoCompletion();
                }
                break;
            }
        });
    }

}