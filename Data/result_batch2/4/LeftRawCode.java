// https://github.com/react-native-linear-gradient/react-native-linear-gradient/tree/741f3d18815b5f02dbba70a376c60ab65fa627c4/android/src/main/java/com/rnlineargradient/LinearGradientView.java#L192-L237
public class TempClass {
    private void drawGradient() {
        // guard against crashes happening while multiple properties are updated
        if (mColors == null || (mLocations != null && mColors.length != mLocations.length))
            return;

        float[] startPoint;
        float[] endPoint;

        if (mUseAngle && mAngleCenter != null) {
            // Angle is in bearing degrees (North = 0, East = 90)
            // convert it to cartesian (N = 90, E = 0)
            float angle = (90 - mAngle);
            float[] relativeStartPoint = getGradientStartPoint(angle, mSize);

            // Get true angleCenter
            float[] angleCenter = new float[]{
                    mAngleCenter[0] * mSize[0],
                    mAngleCenter[1] * mSize[1]
            };
            // Translate to center on angle center
            // Flip Y coordinate to convert from cartesian
            startPoint = new float[]{
                    angleCenter[0] + relativeStartPoint[0],
                    angleCenter[1] - relativeStartPoint[1]
            };
            // Reflect across the center to get the end point
            endPoint = new float[]{
                    angleCenter[0] - relativeStartPoint[0],
                    angleCenter[1] + relativeStartPoint[1]
            };
        } else {
            startPoint = new float[]{mStartPoint[0] * mSize[0], mStartPoint[1] * mSize[1]};
            endPoint = new float[]{mEndPoint[0] * mSize[0], mEndPoint[1] * mSize[1]};
        }

        mShader = new LinearGradient(
                startPoint[0],
                startPoint[1],
                endPoint[0],
                endPoint[1],
                mColors,
                mLocations,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        invalidate();
    }

}