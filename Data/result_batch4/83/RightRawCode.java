// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/libs/slidingmenulibrary/src/com/slidingmenu/lib/CustomViewAbove.java#L812-L824
public class TempClass {
    private int determineTargetPage(float pageOffset, int velocity, int deltaX) {
        int targetPage = mCurItem;
        if (Math.abs(deltaX) > mFlingDistance && Math.abs(velocity) > mMinimumVelocity) {
            if (velocity > 0 && deltaX > 0) {
                targetPage -= 1;
            } else if (velocity < 0 && deltaX < 0) {
                targetPage += 1;
            }
        } else {
            targetPage = (int) Math.round(mCurItem + pageOffset);
        }
        return targetPage;
    }

}