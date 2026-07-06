// https://github.com/WangDaYeeeeee/GeometricWeather/tree/b2373551ea97232176afd8cd82dd75f1ef53c8b8/app/src/main/java/wangdaye/com/geometricweather/common/ui/widgets/horizontal/HorizontalViewPager2.java#L1678-L1694
public class TempClass {
    @UiThread
    boolean beginFakeDrag() {
        if (mScrollEventAdapter.isDragging()) {
            return false;
        }
        mRequestedDragDistance = mActualDraggedDistance = 0;
        mFakeDragBeginTime = SystemClock.uptimeMillis();
        beginFakeVelocityTracker();

        mScrollEventAdapter.notifyBeginFakeDrag();
        if (!mScrollEventAdapter.isIdle()) {
            // Stop potentially running settling animation
            mRecyclerView.stopScroll();
        }
        addFakeMotionEvent(mFakeDragBeginTime, MotionEvent.ACTION_DOWN, 0, 0);
        return true;
    }

}