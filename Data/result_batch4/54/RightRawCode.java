// https://github.com/nextcloud/android/tree/38fa7a3b5d5c156b035326056df5e75c8c174374/app/src/main/java/com/owncloud/android/ui/fragment/FileDetailActivitiesFragment.java#L415-L424
public class TempClass {
    private void hideRefreshLayoutLoader(FragmentActivity activity) {
        activity.runOnUiThread(() -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                binding.swipeContainingList.setRefreshing(false);
                binding.swipeContainingEmpty.setRefreshing(false);
                binding.emptyList.emptyListView.setVisibility(View.GONE);
                isLoadingActivities = false;
            }
        });
    }

}