// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/fragments/OnScrollBelowItemsListener.java#L13-L39
public class TempClass {
    @Override
    public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            int pastVisibleItems = 0;
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

            final int visibleItemCount = layoutManager.getChildCount();
            final int totalItemCount = layoutManager.getItemCount();

            // Already covers the GridLayoutManager case
            if (layoutManager instanceof LinearLayoutManager) {
                pastVisibleItems = ((LinearLayoutManager) layoutManager)
                        .findFirstVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                final int[] positions = ((StaggeredGridLayoutManager) layoutManager)
                        .findFirstVisibleItemPositions(null);
                if (positions != null && positions.length > 0) {
                    pastVisibleItems = positions[0];
                }
            }

            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                onScrolledDown(recyclerView);
            }
        }
    }

}