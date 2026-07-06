// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/VersionedGestureDetector.java#L195-L222
public class TempClass {
		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			final int action = ev.getAction();
			switch (action & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN:
					mActivePointerId = ev.getPointerId(0);
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					mActivePointerId = INVALID_POINTER_ID;
					break;
				case MotionEvent.ACTION_POINTER_UP:
					final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
					final int pointerId = ev.getPointerId(pointerIndex);
					if (pointerId == mActivePointerId) {
						// This was our active pointer going up. Choose a new
						// active pointer and adjust accordingly.
						final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
						mActivePointerId = ev.getPointerId(newPointerIndex);
						mLastTouchX = ev.getX(newPointerIndex);
						mLastTouchY = ev.getY(newPointerIndex);
					}
					break;
			}

			mActivePointerIndex = ev.findPointerIndex(mActivePointerId != INVALID_POINTER_ID ? mActivePointerId : 0);
			return super.onTouchEvent(ev);
		}

}