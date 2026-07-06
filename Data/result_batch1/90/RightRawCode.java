// https://github.com/open-keychain/open-keychain/tree/8d0bd1f8537fb3a795fda8161f730a313ef7b01c/OpenKeychain/src/main/java/org/sufficientlysecure/keychain/ui/util/recyclerview/DividerItemDecoration.java#L86-L100
public class TempClass {
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount -1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}