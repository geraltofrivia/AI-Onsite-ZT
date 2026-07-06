// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/page/animation/SimulationPageAnim.java#L89-L107
public class TempClass {
    @Override
    public void drawMove(Canvas canvas) {
        switch (mDirection) {
            case NEXT:
                calcPoints();
                drawCurrentPageArea(canvas, mCurBitmap, mPath0);
                drawNextPageAreaAndShadow(canvas, mNextBitmap);
                drawCurrentPageShadow(canvas);
                drawCurrentBackArea(canvas, mCurBitmap);
                break;
            default:
                calcPoints();
                drawCurrentPageArea(canvas, mNextBitmap, mPath0);
                drawNextPageAreaAndShadow(canvas, mCurBitmap);
                drawCurrentPageShadow(canvas);
                drawCurrentBackArea(canvas, mNextBitmap);
                break;
        }
    }

}