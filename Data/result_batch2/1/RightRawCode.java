// https://github.com/open-keychain/open-keychain/tree/8d0bd1f8537fb3a795fda8161f730a313ef7b01c/OpenKeychain/src/main/java/org/sufficientlysecure/keychain/ui/util/recyclerview/DividerItemDecoration.java#L67-L84
public class TempClass {
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        if (!showAfterLast) {
            childCount -= 1;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}