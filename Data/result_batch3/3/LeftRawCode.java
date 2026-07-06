// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/PhotoViewAttacher.java#L276-L303
public class TempClass {
	public final void onDrag(float dx, float dy) {
		if (DEBUG) {
			Log.d(LOG_TAG, String.format("onDrag: dx: %.2f. dy: %.2f", dx, dy));
		}

		ImageView imageView = getImageView();

		if (null != imageView && hasDrawable(imageView)) {
			mSuppMatrix.postTranslate(dx, dy);
			checkAndDisplayMatrix();

			/**
			 * Here we decide whether to let the ImageView's parent to start
			 * taking over the touch event.
			 *
			 * First we check whether this function is enabled. We never want the
             * parent to take over if we're scaling. We then check the edge we're
             * on, and the direction of the scroll (i.e. if we're pulling against
             * the edge, aka 'overscrolling', let the parent take over).
			 */
			if (mAllowParentInterceptOnEdge && !mScaleDragDetector.isScaling()) {
				if (mScrollEdge == EDGE_BOTH || (mScrollEdge == EDGE_LEFT && dx >= 1f)
						|| (mScrollEdge == EDGE_RIGHT && dx <= -1f)) {
					imageView.getParent().requestDisallowInterceptTouchEvent(false);
				}
			}
		}
	}

}