// https://github.com/MiPushFramework/MiPushFramework/tree/1425051fdf5dc20a5d4c6bed2dd821ccd5123acc/push/src/main/java/android/support/design/internal/BaselineLayout.java#L86-L113
public class TempClass {
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        final int parentLeft = getPaddingLeft();
        final int parentRight = right - left - getPaddingRight();
        final int parentContentWidth = parentRight - parentLeft;
        final int parentTop = getPaddingTop();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();

            final int childLeft = parentLeft + (parentContentWidth - width) / 2;
            final int childTop;
            if (mBaseline != -1 && child.getBaseline() != -1) {
                childTop = parentTop + mBaseline - child.getBaseline();
            } else {
                childTop = parentTop;
            }

            child.layout(childLeft, childTop, childLeft + width, childTop + height);
        }
    }

}