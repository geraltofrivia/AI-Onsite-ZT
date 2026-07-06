// https://github.com/alibaba/vlayout/tree/00d6be0a1fa89c2f54ca923d2ea2e34d279d289e/vlayout/src/main/java/com/alibaba/android/vlayout/VirtualLayoutManager.java#L521-L584
public class TempClass {
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.beginSection(TRACE_LAYOUT);
        }

        if (mNoScrolling && state.didStructureChange()) {
            mSpaceMeasured = false;
            mSpaceMeasuring = true;
        }


        runPreLayout(recycler, state);

        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // MaX_VALUE means invalidate scrolling offset - no scroll
            runPostLayout(recycler, state, Integer.MAX_VALUE); // hack to indicate its an initial layout
        }


        if ((mNestedScrolling || mNoScrolling) && mSpaceMeasuring) {
            // measure required, so do measure
            mSpaceMeasured = true;
            // get last child
            int childCount = getChildCount();
            View lastChild = getChildAt(childCount - 1);
            if (lastChild != null) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) lastChild.getLayoutParams();
                // found the end of last child view
                mMeasuredFullSpace = getDecoratedBottom(lastChild) + params.bottomMargin + computeAlignOffset(lastChild, true, false);

                if (mRecyclerView != null && mNestedScrolling) {
                    ViewParent parent = mRecyclerView.getParent();
                    if (parent instanceof View) {
                        // make sure the fullspace be the min value of measured space and parent's height
                        mMeasuredFullSpace = Math.min(mMeasuredFullSpace, ((View) parent).getMeasuredHeight());
                    }
                }
            } else {
                mSpaceMeasuring = false;
            }
            mSpaceMeasuring = false;
            if (mRecyclerView != null && getItemCount() > 0) {
                // relayout
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        // post relayout
                        if (mRecyclerView != null)
                            mRecyclerView.requestLayout();
                    }
                });
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }
    }

}