// https://github.com/WangDaYeeeeee/GeometricWeather/tree/b2373551ea97232176afd8cd82dd75f1ef53c8b8/app/src/main/java/wangdaye/com/geometricweather/common/ui/widgets/RoundProgress.java#L103-L127
public class TempClass {
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = mBackgroundRectF.height() / 2f;
        mProgressPaint.setColor(mBackgroundColor);
        canvas.drawRoundRect(mBackgroundRectF, radius, radius, mProgressPaint);

        mProgressRectF.set(
                mBackgroundRectF.left,
                mBackgroundRectF.top,
                mBackgroundRectF.left + mBackgroundRectF.width() * mProgress / mMax,
                mBackgroundRectF.bottom
        );
        mProgressPaint.setColor(mProgressColor);
        if (mProgressRectF.width() < 2 * radius) {
            canvas.drawCircle(
                    mProgressRectF.left + radius,
                    mProgressRectF.top + radius,
                    radius,
                    mProgressPaint
            );
        } else {
            canvas.drawRoundRect(mProgressRectF, radius, radius, mProgressPaint);
        }
    }

}