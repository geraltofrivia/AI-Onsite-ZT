// https://github.com/PojavLauncherTeam/PojavLauncher/tree/6eb830ba7aa8465d872a9ef0a8d87592c978e292/app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/modloaders/FabriclikeDownloadTask.java#L33-L43
public class TempClass {
    @Override
    public void run() {
        ProgressKeeper.submitProgress(ProgressLayout.INSTALL_MODPACK, 0, R.string.fabric_dl_progress);
        try {
            if(runCatching()) mModloaderDownloadListener.onDownloadFinished(null);
            else mModloaderDownloadListener.onDataNotAvailable();
        }catch (IOException e) {
            mModloaderDownloadListener.onDownloadError(e);
        }
        ProgressLayout.clearProgress(ProgressLayout.INSTALL_MODPACK);
    }

}