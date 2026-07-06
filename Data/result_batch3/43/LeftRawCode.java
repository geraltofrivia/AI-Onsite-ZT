// https://github.com/prolificinteractive/material-calendarview/tree/04fae8175fd034d0a7131f8cb253cae883a88aa2/library/src/main/java/com/prolificinteractive/materialcalendarview/CalendarPagerAdapter.java#L265-L285
public class TempClass {
  public void setRangeDates(CalendarDay min, CalendarDay max) {
    this.minDate = min;
    this.maxDate = max;
    for (V pagerView : currentViews) {
      pagerView.setMinimumDate(min);
      pagerView.setMaximumDate(max);
    }

    if (min == null) {
      min = CalendarDay.from(today.getYear() - 200, today.getMonth(), today.getDay());
    }

    if (max == null) {
      max = CalendarDay.from(today.getYear() + 200, today.getMonth(), today.getDay());
    }

    rangeIndex = createRangeIndex(min, max);

    notifyDataSetChanged();
    invalidateSelectedDates();
  }

}