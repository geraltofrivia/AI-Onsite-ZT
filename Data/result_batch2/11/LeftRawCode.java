// https://github.com/prolificinteractive/material-calendarview/tree/04fae8175fd034d0a7131f8cb253cae883a88aa2/library/src/main/java/com/prolificinteractive/materialcalendarview/MaterialCalendarView.java#L1684-L1709
public class TempClass {
  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    final int count = getChildCount();

    final int parentLeft = getPaddingLeft();
    final int parentWidth = right - left - parentLeft - getPaddingRight();

    int childTop = getPaddingTop();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() == View.GONE) {
        continue;
      }

      final int width = child.getMeasuredWidth();
      final int height = child.getMeasuredHeight();

      int delta = (parentWidth - width) / 2;
      int childLeft = parentLeft + delta;

      child.layout(childLeft, childTop, childLeft + width, childTop + height);

      childTop += height;
    }
  }

}