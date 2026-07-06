// https://github.com/k0shk0sh/FastHub/tree/fb7053da2079b624d6129ef81370a9d5774bcb9a/app/src/main/java/com/fastaccess/ui/widgets/recyclerview/scroll/InfiniteScroll.java#L46-L91
public class TempClass {
    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (newlyAdded) {
            newlyAdded = false;
            return;
        }
        onScrolled(dy > 0);
        if (layoutManager == null) {
            initLayoutManager(recyclerView.getLayoutManager());
        }
        if (adapter == null) {
            if (recyclerView.getAdapter() instanceof BaseRecyclerAdapter) {
                adapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
            }
        }
        if (adapter != null && adapter.isProgressAdded()) return;

        int lastVisibleItemPosition = 0;
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            boolean isCallingApi = onLoadMore(currentPage, totalItemCount);
            loading = true;
            if (adapter != null && isCallingApi) {
                adapter.addProgress();
            }
        }
    }

}