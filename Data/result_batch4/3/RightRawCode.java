// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/player/helper/AudioReactor.java#L84-L99
public class TempClass {
    @Override
    public void onAudioFocusChange(final int focusChange) {
        Log.d(TAG, "onAudioFocusChange() called with: focusChange = [" + focusChange + "]");
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                onAudioFocusGain();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                onAudioFocusLossCanDuck();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                onAudioFocusLoss();
                break;
        }
    }

}