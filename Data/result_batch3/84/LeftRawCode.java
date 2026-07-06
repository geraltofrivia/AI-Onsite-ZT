// https://github.com/Querz/mcaselector/tree/0accb627453cd01a9b470eaace54b9caaefc831d/src/main/java/net/querz/mcaselector/ui/component/RangeSlider.java#L74-L121
public class TempClass {
	private void onMouseDragged(MouseEvent e) {
		double x = Math.max(Math.min(e.getX(), getWidth() + upperThumb.getWidth() / 2), -lowerThumb.getWidth() / 2);

		Point2f mouseLocation = new Point2f(x, e.getY());

		if (pressedPane == lowerThumb) {
			if (x > upperThumb.getLayoutX()) {
				setLow(getHigh());
				update();
				return;
			}
			double offset = x - previousMouseLocation.getX();
			double rangeOffset = offset * ((getMax() - getMin()) / getWidth());
			setLow(getLow() + rangeOffset);
			update();
		} else if (pressedPane == upperThumb) {
			if (x < lowerThumb.getLayoutX() + lowerThumb.getWidth()) {
				setHigh(getLow());
				update();
				return;
			}
			double offset = x - previousMouseLocation.getX();
			double rangeOffset = offset * ((getMax() - getMin()) / getWidth());
			setHigh(getHigh() + rangeOffset);
			update();
		} else if (pressedPane == range) {
			double offset = x - previousMouseLocation.getX();
			double rangeOffset = offset * ((getMax() - getMin()) / getWidth());

			// check if this would move the range below min
			if (getLow() + rangeOffset < getMin()) {
				// calculate offset to 0
				rangeOffset = getMin() - getLow();
			}

			// check if this would move the range above max
			if (getHigh() + rangeOffset > getMax()) {
				// calculate offset to max
				rangeOffset = getMax() - getHigh();
			}

			setLow(getLow() + rangeOffset);
			setHigh(getHigh() + rangeOffset);
			update();
		}

		previousMouseLocation = mouseLocation;
	}

}