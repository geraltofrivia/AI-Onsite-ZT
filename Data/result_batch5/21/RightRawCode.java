// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/allapps/ActivityAllAppsContainerView.java#L683-L697
public class TempClass {
    private static void setUpCustomRecyclerViewPool(
            @NonNull AllAppsRecyclerView mainRecyclerView,
            @Nullable AllAppsRecyclerView workRecyclerView,
            @NonNull RecyclerView.RecycledViewPool recycledViewPool) {
        if (!ENABLE_ALL_APPS_RV_PREINFLATION.get()) {
            return;
        }
        mainRecyclerView.setRecycledViewPool(recycledViewPool);
        if (workRecyclerView != null) {
            workRecyclerView.setRecycledViewPool(recycledViewPool);
        }
        if (ALL_APPS_GONE_VISIBILITY.get()) {
            mainRecyclerView.updatePoolSize();
        }
    }

}