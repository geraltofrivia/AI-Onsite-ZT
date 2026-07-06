// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/PhotoViewAttacher.java#L414-L457
public class TempClass {
	@Override
	public final boolean onTouch(View v, MotionEvent ev) {
		boolean handled = false;

		if (mZoomEnabled) {
			switch (ev.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// First, disable the Parent from intercepting the touch
					// event
					v.getParent().requestDisallowInterceptTouchEvent(true);

					// If we're flinging, and the user presses down, cancel
					// fling
					cancelFling();
					break;

				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					// If the user has zoomed less than min scale, zoom back
					// to min scale

					if (getScale() < mMinScale) {
						RectF rect = getDisplayRect();
						if (null != rect) {
							v.post(new AnimatedZoomRunnable(getScale(), mMinScale, rect.centerX(), rect.centerY()));
							handled = true;
						}
					}
					break;
			}

			// Check to see if the user double tapped
			if (null != mGestureDetector && mGestureDetector.onTouchEvent(ev)) {
				handled = true;
			}

			// Finally, try the Scale/Drag detector
			if (null != mScaleDragDetector && mScaleDragDetector.onTouchEvent(ev)) {
				handled = true;
			}
		}

		return handled;
	}

}