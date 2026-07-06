// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/PhotoViewAttacher.java#L846-L869
public class TempClass {
		public void run() {
			ImageView imageView = getImageView();

			if (null != imageView) {
				mSuppMatrix.postScale(mDeltaScale, mDeltaScale, mFocalX, mFocalY);
				checkAndDisplayMatrix();

				final float currentScale = getScale();

				if ((mDeltaScale > 1f && currentScale < mTargetZoom)
						|| (mDeltaScale < 1f && mTargetZoom < currentScale)) {
					// We haven't hit our target scale yet, so post ourselves
					// again
					Compat.postOnAnimation(imageView, this);

				} else {
					// We've scaled past our target zoom, so calculate the
					// necessary scale so we're back at target zoom
					final float delta = mTargetZoom / currentScale;
					mSuppMatrix.postScale(delta, delta, mFocalX, mFocalY);
					checkAndDisplayMatrix();
				}
			}
		}

}