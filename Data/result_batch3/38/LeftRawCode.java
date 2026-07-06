// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/VersionedGestureDetector.java#L89-L164
public class TempClass {
		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			switch (ev.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					mVelocityTracker = VelocityTracker.obtain();
					mVelocityTracker.addMovement(ev);

					mLastTouchX = getActiveX(ev);
					mLastTouchY = getActiveY(ev);
					mIsDragging = false;
					break;
				}

				case MotionEvent.ACTION_MOVE: {
					final float x = getActiveX(ev);
					final float y = getActiveY(ev);
					final float dx = x - mLastTouchX, dy = y - mLastTouchY;

					if (!mIsDragging) {
						// Use Pythagoras to see if drag length is larger than
						// touch slop
						mIsDragging = Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
					}

					if (mIsDragging) {
						mListener.onDrag(dx, dy);
						mLastTouchX = x;
						mLastTouchY = y;

						if (null != mVelocityTracker) {
							mVelocityTracker.addMovement(ev);
						}
					}
					break;
				}

				case MotionEvent.ACTION_CANCEL: {
					// Recycle Velocity Tracker
					if (null != mVelocityTracker) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
					break;
				}

				case MotionEvent.ACTION_UP: {
					if (mIsDragging) {
						if (null != mVelocityTracker) {
							mLastTouchX = getActiveX(ev);
							mLastTouchY = getActiveY(ev);

							// Compute velocity within the last 1000ms
							mVelocityTracker.addMovement(ev);
							mVelocityTracker.computeCurrentVelocity(1000);

							final float vX = mVelocityTracker.getXVelocity(), vY = mVelocityTracker.getYVelocity();

							// If the velocity is greater than minVelocity, call
							// listener
							if (Math.max(Math.abs(vX), Math.abs(vY)) >= mMinimumVelocity) {
								mListener.onFling(mLastTouchX, mLastTouchY, -vX, -vY);
							}
						}
					}

					// Recycle Velocity Tracker
					if (null != mVelocityTracker) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
					break;
				}
			}

			return true;
		}

}