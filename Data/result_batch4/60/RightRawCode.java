// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/uk/co/senab/photoview/PhotoViewAttacher.java#L978-L996
public class TempClass {
        @Override
        public void run() {
            ImageView imageView = getImageView();
            if (imageView == null) {
                return;
            }

            float t = interpolate();
            float scale = mZoomStart + t * (mZoomEnd - mZoomStart);
            float deltaScale = scale / getScale();

            mSuppMatrix.postScale(deltaScale, deltaScale, mFocalX, mFocalY);
            checkAndDisplayMatrix();

            // We haven't hit our target scale yet, so post ourselves again
            if (t < 1f) {
                Compat.postOnAnimation(imageView, this);
            }
        }

}