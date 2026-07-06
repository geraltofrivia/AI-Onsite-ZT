// https://github.com/ThirtyDegreesRay/OpenHub/tree/fcb9b422e8013169e2328a6e09c675ce11a27ad6/app/src/main/java/com/thirtydegreesray/openhub/ui/adapter/base/DividerItemDecoration.java#L76-L92
public class TempClass {
    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override

}