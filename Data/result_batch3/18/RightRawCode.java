// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/libs/slidingmenulibrary/src/com/slidingmenu/lib/CustomViewAbove.java#L488-L510
public class TempClass {
    @Override
    public void computeScroll() {
        if (!mScroller.isFinished()) {
            if (mScroller.computeScrollOffset()) {
                int oldX = getScrollX();
                int oldY = getScrollY();
                int x = mScroller.getCurrX();
                int y = mScroller.getCurrY();

                if (oldX != x || oldY != y) {
                    scrollTo(x, y);
                    pageScrolled(x);
                }

                // Keep on drawing until the animation has finished.
                invalidate();
                return;
            }
        }

        // Done with scroll, clean up state.
        completeScroll();
    }

}