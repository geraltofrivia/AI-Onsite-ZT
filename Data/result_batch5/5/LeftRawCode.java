// https://github.com/Ramotion/folding-cell-android/tree/d8736d1bd5071dc61a36548e708fbd055af31ff2/folding-cell/src/main/java/com/ramotion/foldingcell/animations/FoldAnimation.java#L84-L98
public class TempClass {
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        final float fromDegrees = mFromDegrees;
        final float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

        camera.save();
        camera.rotateX(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);
    }

}