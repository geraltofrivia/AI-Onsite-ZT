// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/libs/slidingmenulibrary/src/com/slidingmenu/lib/CustomViewAbove.java#L219-L242
public class TempClass {
    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always, int velocity) {
        if (!always && mCurItem == item) {
            setScrollingCacheEnabled(false);
            return;
        }

        item = mViewBehind.getMenuPage(item);

        final boolean dispatchSelected = mCurItem != item;
        mCurItem = item;
        final int destX = getDestScrollX(mCurItem);
        if (dispatchSelected && mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(item);
        }
        if (dispatchSelected && mInternalPageChangeListener != null) {
            mInternalPageChangeListener.onPageSelected(item);
        }
        if (smoothScroll) {
            smoothScrollTo(destX, 0, velocity);
        } else {
            completeScroll();
            scrollTo(destX, 0);
        }
    }

}