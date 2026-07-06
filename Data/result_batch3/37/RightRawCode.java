// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/me/imid/swipebacklayout/lib/ViewDragHelper.java#L1008-L1034
public class TempClass {
    protected boolean canScroll(View v, boolean checkV, int dx, int dy, int x, int y) {
        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            // Count backwards - let topmost views consume scroll distance
            // first.
            for (int i = count - 1; i >= 0; i--) {
                // TODO: Add versioned support here for transformed views.
                // This will not work for transformed views in Honeycomb+
                final View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft()
                        && x + scrollX < child.getRight()
                        && y + scrollY >= child.getTop()
                        && y + scrollY < child.getBottom()
                        && canScroll(child, true, dx, dy, x + scrollX - child.getLeft(), y
                        + scrollY - child.getTop())) {
                    return true;
                }
            }
        }

        return checkV
                && (ViewCompat.canScrollHorizontally(v, -dx) || ViewCompat.canScrollVertically(v,
                -dy));
    }

}