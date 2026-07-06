// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/PhotoViewAttacher.java#L580-L639
public class TempClass {
	private void checkMatrixBounds() {
		final ImageView imageView = getImageView();
		if (null == imageView) {
			return;
		}

		final RectF rect = getDisplayRect(getDisplayMatrix());
		if (null == rect) {
			return;
		}

		final float height = rect.height(), width = rect.width();
		float deltaX = 0, deltaY = 0;

		final int viewHeight = imageView.getHeight();
		if (height <= viewHeight) {
			switch (mScaleType) {
				case FIT_START:
					deltaY = -rect.top;
					break;
				case FIT_END:
					deltaY = viewHeight - height - rect.top;
					break;
				default:
					deltaY = (viewHeight - height) / 2 - rect.top;
					break;
			}
		} else if (rect.top > 0) {
			deltaY = -rect.top;
		} else if (rect.bottom < viewHeight) {
			deltaY = viewHeight - rect.bottom;
		}

		final int viewWidth = imageView.getWidth();
		if (width <= viewWidth) {
			switch (mScaleType) {
				case FIT_START:
					deltaX = -rect.left;
					break;
				case FIT_END:
					deltaX = viewWidth - width - rect.left;
					break;
				default:
					deltaX = (viewWidth - width) / 2 - rect.left;
					break;
			}
			mScrollEdge = EDGE_BOTH;
		} else if (rect.left > 0) {
			mScrollEdge = EDGE_LEFT;
			deltaX = -rect.left;
		} else if (rect.right < viewWidth) {
			deltaX = viewWidth - rect.right;
			mScrollEdge = EDGE_RIGHT;
		} else {
			mScrollEdge = EDGE_NONE;
		}

		// Finally actually translate the matrix
		mSuppMatrix.postTranslate(deltaX, deltaY);
	}

}