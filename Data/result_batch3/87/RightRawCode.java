// https://github.com/Haleydu/Cimoc/tree/891eb3f7ce51a940fd144795798c03b8e2058c0f/app/src/main/java/com/haleydu/cimoc/ui/widget/rvp/RecyclerViewPager.java#L183-L194
public class TempClass {
    public int getCurrentPosition() {
        int curPosition;
        if (getLayoutManager().canScrollHorizontally()) {
            curPosition = RecyclerViewUtils.getCenterXChildPosition(this);
        } else {
            curPosition = RecyclerViewUtils.getCenterYChildPosition(this);
        }
        if (curPosition < 0) {
            curPosition = mSmoothScrollTargetPosition;
        }
        return curPosition;
    }

}