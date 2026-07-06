// https://github.com/WangDaYeeeeee/GeometricWeather/tree/b2373551ea97232176afd8cd82dd75f1ef53c8b8/app/src/main/java/wangdaye/com/geometricweather/main/layouts/MainLayoutManager.java#L48-L102
public class TempClass {
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mDataSetChanged) {
            removeAndRecycleAllViews(recycler);
        } else {
            detachAndScrapAttachedViews(recycler);
        }

        if (state.getItemCount() == 0 || state.isPreLayout()) {
            return;
        }
        if (getItemCount() == 0) {
            return;
        }

        int y = 0;
        if (!getClipToPadding()) {
            y += getPaddingTop();
        }

        int childHeight;
        ViewGroup.MarginLayoutParams params;
        for (int i = 0; i < getItemCount(); i ++) {
            View child = recycler.getViewForPosition(i);
            addView(child);

            measureChildWithMargins(child, 0, 0);
            childHeight = getDecoratedMeasuredHeight(child);
            params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            layoutDecoratedWithMargins(
                    child,
                    getPaddingLeft(),
                    y,
                    getWidth() - getPaddingRight(),
                    y + childHeight + params.topMargin + params.bottomMargin
            );

            y += childHeight + params.topMargin + params.bottomMargin;
        }

        if (!getClipToPadding()) {
            y += getPaddingBottom();
        }

        mMeasuredHeight = y;

        if (mDataSetChanged) {
            mScrollOffset = 0;
            mDataSetChanged = false;
        } else {
            int oldOffset = mScrollOffset;
            mScrollOffset = 0;
            scrollVerticallyBy(oldOffset, recycler, state);
        }
    }

}