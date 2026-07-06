// https://github.com/alibaba/vlayout/tree/00d6be0a1fa89c2f54ca923d2ea2e34d279d289e/vlayout/src/main/java/com/alibaba/android/vlayout/VirtualLayoutManager.java#L590-L634
public class TempClass {
    @Override
    protected int scrollInternalBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.beginSection(TRACE_SCROLL);
        }

        runPreLayout(recycler, state);

        int scrolled = 0;
        try {
            if (!mNoScrolling) {
                scrolled = super.scrollInternalBy(dy, recycler, state);
            } else {
                if (getChildCount() == 0 || dy == 0) {
                    return 0;
                }

                mLayoutState.mRecycle = true;
                ensureLayoutStateExpose();
                final int layoutDirection = dy > 0 ? LayoutState.LAYOUT_END : LayoutState.LAYOUT_START;
                final int absDy = Math.abs(dy);
                updateLayoutStateExpose(layoutDirection, absDy, true, state);
                final int freeScroll = mLayoutState.mScrollingOffset;

                final int consumed = freeScroll + fill(recycler, mLayoutState, state, false);
                if (consumed < 0) {
                    return 0;
                }
                scrolled = absDy > consumed ? layoutDirection * consumed : dy;
            }
        } catch (Exception e) {
            Log.w(TAG, Log.getStackTraceString(e), e);
            if (sDebuggable)
                throw e;

        } finally {
            runPostLayout(recycler, state, scrolled);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }

        return scrolled;
    }

}