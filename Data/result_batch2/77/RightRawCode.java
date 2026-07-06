// https://github.com/Haleydu/Cimoc/tree/891eb3f7ce51a940fd144795798c03b8e2058c0f/app/src/main/java/com/haleydu/cimoc/ui/widget/FlingRunnable.java#L56-L70
public class TempClass {
    @Override
    public void run() {
        if (mScroller.isFinished()) {
            return;
        }

        if (mScroller.computeScrollOffset()) {
            final int newX = mScroller.getCurrX();
            final int newY = mScroller.getCurrY();
            mListener.onFlingRunning(mCurrentX - newX, mCurrentY - newY);
            mCurrentX = newX;
            mCurrentY = newY;
            ViewUtils.postOnAnimation(mView, this);
        }
    }

}