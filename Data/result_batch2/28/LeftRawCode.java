// https://github.com/ybq/Android-SpinKit/tree/aa83e4d0a42c76eae15e8b18b79aa2a231cca5f8/library/src/main/java/com/github/ybq/android/spinkit/animation/interpolator/PathInterpolatorDonut.java#L63-L95
public class TempClass {
    @Override
    public float getInterpolation(float t) {
        if (t <= 0.0f) {
            return 0.0f;
        } else if (t >= 1.0f) {
            return 1.0f;
        }

        // Do a binary search for the correct x to interpolate between.
        int startIndex = 0;
        int endIndex = mX.length - 1;
        while (endIndex - startIndex > 1) {
            int midIndex = (startIndex + endIndex) / 2;
            if (t < mX[midIndex]) {
                endIndex = midIndex;
            } else {
                startIndex = midIndex;
            }
        }

        final float xRange = mX[endIndex] - mX[startIndex];
        if (xRange == 0) {
            return mY[startIndex];
        }

        final float tInRange = t - mX[startIndex];
        final float fraction = tInRange / xRange;

        final float startY = mY[startIndex];
        final float endY = mY[endIndex];

        return startY + (fraction * (endY - startY));
    }

}