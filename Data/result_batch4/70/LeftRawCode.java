// https://github.com/prolificinteractive/material-calendarview/tree/04fae8175fd034d0a7131f8cb253cae883a88aa2/library/src/main/java/com/prolificinteractive/materialcalendarview/MaterialCalendarView.java#L1085-L1099
public class TempClass {
  @Override
  protected Parcelable onSaveInstanceState() {
    SavedState ss = new SavedState(super.onSaveInstanceState());
    ss.showOtherDates = getShowOtherDates();
    ss.allowClickDaysOutsideCurrentMonth = allowClickDaysOutsideCurrentMonth();
    ss.minDate = getMinimumDate();
    ss.maxDate = getMaximumDate();
    ss.selectedDates = getSelectedDates();
    ss.selectionMode = getSelectionMode();
    ss.topbarVisible = getTopbarVisible();
    ss.dynamicHeightEnabled = mDynamicHeightEnabled;
    ss.currentMonth = currentMonth;
    ss.cacheCurrentPosition = state.cacheCurrentPosition;
    return ss;
  }

}