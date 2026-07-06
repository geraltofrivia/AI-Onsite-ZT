// https://github.com/ThirtyDegreesRay/OpenHub/tree/fcb9b422e8013169e2328a6e09c675ce11a27ad6/app/src/main/java/com/thirtydegreesray/openhub/ui/adapter/base/DividerItemDecoration.java#L59-L76
public class TempClass {
    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent) {

}