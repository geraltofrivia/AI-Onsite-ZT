// https://github.com/limboemu/limbo/tree/887c6a68cd6b414377d7f8071bc12bbc16e59809/limbo-android-lib/src/main/java/com/max2idea/android/limbo/main/LimboSDLActivity.java#L1112-L1125
public class TempClass {
    public synchronized void setFullscreen() {
        if(!machineRunning) {
            Log.w(TAG, "Machine not running not reset layout");
            return;
        }
        resettingLayout = true;
        try {
            Log.d(TAG, "Requesting fullscreen");
            viewListener.onAction(MachineAction.FULLSCREEN, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resettingLayout = false;
    }

}