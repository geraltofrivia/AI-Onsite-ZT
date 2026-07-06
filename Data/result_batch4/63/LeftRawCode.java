// https://github.com/prolificinteractive/material-calendarview/tree/04fae8175fd034d0a7131f8cb253cae883a88aa2/sample/src/main/java/com/prolificinteractive/materialcalendarview/sample/DisableDaysActivity.java#L21-L42
public class TempClass {
  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic);
    ButterKnife.bind(this);

    // Add a decorator to disable prime numbered days
    widget.addDecorator(new PrimeDayDisableDecorator());
    // Add a second decorator that explicitly enables days <= 10. This will work because
    // decorators are applied in order, and the system allows re-enabling
    widget.addDecorator(new EnableOneToTenDecorator());

    final LocalDate calendar = LocalDate.now();
    widget.setSelectedDate(calendar);

    final LocalDate min = LocalDate.of(calendar.getYear(), Month.JANUARY, 1);
    final LocalDate max = LocalDate.of(calendar.getYear() + 1, Month.OCTOBER, 31);

    widget.state().edit()
        .setMinimumDate(min)
        .setMaximumDate(max)
        .commit();
  }

}