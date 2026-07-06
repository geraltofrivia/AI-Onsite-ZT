// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/page/animation/SimulationPageAnim.java#L507-L542
public class TempClass {
    private void drawCurrentPageArea(Canvas canvas, Bitmap bitmap, Path path) {
        mPath0.reset();
        mPath0.moveTo(mBezierStart1.x, mBezierStart1.y);
        mPath0.quadTo(mBezierControl1.x, mBezierControl1.y, mBezierEnd1.x,
                mBezierEnd1.y);
        mPath0.lineTo(mTouchX, mTouchY);
        mPath0.lineTo(mBezierEnd2.x, mBezierEnd2.y);
        mPath0.quadTo(mBezierControl2.x, mBezierControl2.y, mBezierStart2.x,
                mBezierStart2.y);
        mPath0.lineTo(mCornerX, mCornerY);
        mPath0.close();

        canvas.save();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mXORPath.reset();
            mXORPath.moveTo(0f, 0f);
            mXORPath.lineTo(canvas.getWidth(), 0f);
            mXORPath.lineTo(canvas.getWidth(), canvas.getHeight());
            mXORPath.lineTo(0f, canvas.getHeight());
            mXORPath.close();

            // 取 path 的补给，作为 canvas 的交集
            mXORPath.op(path, Path.Op.XOR);
            canvas.clipPath(mXORPath);
        } else {
            canvas.clipPath(path, Region.Op.XOR);
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
        try {
            canvas.restore();
        } catch (Exception e) {

        }

    }

}