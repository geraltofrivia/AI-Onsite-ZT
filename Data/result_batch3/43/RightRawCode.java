// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/core/ui/components/calendar/JDayChooser.java#L923-L941
public class TempClass {
	public void setSelectableDateRange(final Date min, final Date max) {
		if (min == null) {
			minSelectableDate = defaultMinSelectableDate;
		}
		else {
			minSelectableDate = min;
		}
		if (max == null) {
			maxSelectableDate = defaultMaxSelectableDate;
		}
		else {
			maxSelectableDate = max;
		}
		if (maxSelectableDate.before(minSelectableDate)) {
			minSelectableDate = defaultMinSelectableDate;
			maxSelectableDate = defaultMaxSelectableDate;
		}
		updateDays();
	}

}