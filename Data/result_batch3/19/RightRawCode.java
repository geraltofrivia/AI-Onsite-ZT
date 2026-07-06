// https://github.com/open-keychain/open-keychain/tree/8d0bd1f8537fb3a795fda8161f730a313ef7b01c/OpenKeychain/src/main/java/org/sufficientlysecure/keychain/ui/adapter/SpacesItemDecoration.java#L46-L92
public class TempClass {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int position = params.getViewPosition();
        final int spanSize;
        final int index;
        if (params instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams gridParams = (GridLayoutManager.LayoutParams) params;
            spanSize = gridParams.getSpanSize();
            index = gridParams.getSpanIndex();
        } else {
            spanSize = 1;
            index = position % spanCount;
        }
        // invalid value
        if (spanSize < 1 || index < 0) return;

        if (spanSize == spanCount) { // full span
            outRect.left = space;
            outRect.right = space;
        } else {
            if (index == 0) { // left one
                outRect.left = space;
            }
            // spanCount >= 1
            if (index == spanCount - 1) { // right one
                outRect.right = space;
            }
            if (outRect.left == 0) {
                outRect.left = space / 2;
            }
            if (outRect.right == 0) {
                outRect.right = space / 2;
            }
        }
        // set top to all in first lane
        if (position < spanCount && spanSize <= spanCount) {
            if (lastItemInFirstLane < 0) { // lay out at first time
                lastItemInFirstLane = position + spanSize == spanCount ? position : lastItemInFirstLane;
                outRect.top = space;
            } else if (position <= lastItemInFirstLane) { // scroll to first lane again
                outRect.top = space;
            }
        }
        outRect.bottom = space;

    }

}