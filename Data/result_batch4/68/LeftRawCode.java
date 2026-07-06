// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/page/animation/SlidePageAnim.java#L34-L74
public class TempClass {
    @Override
    public void drawMove(Canvas canvas) {
        int dis = 0;
        switch (mDirection) {
            case NEXT:
                //左半边的剩余区域
                dis = (int) (mScreenWidth - mStartX + mTouchX);
                if (dis > mScreenWidth) {
                    dis = mScreenWidth;
                }
                //计算bitmap截取的区域
                mSrcRect.left = mScreenWidth - dis;
                //计算bitmap在canvas显示的区域
                mDestRect.right = dis;
                //计算下一页截取的区域
                mNextSrcRect.right = mScreenWidth - dis;
                //计算下一页在canvas显示的区域
                mNextDestRect.left = dis;

                canvas.drawBitmap(mNextBitmap, mNextSrcRect, mNextDestRect, null);
                canvas.drawBitmap(mCurBitmap, mSrcRect, mDestRect, null);
                break;
            default:
                dis = (int) (mTouchX - mStartX);
                if (dis < 0) {
                    dis = 0;
                    mStartX = mTouchX;
                }
                mSrcRect.left = mScreenWidth - dis;
                mDestRect.right = dis;

                //计算下一页截取的区域
                mNextSrcRect.right = mScreenWidth - dis;
                //计算下一页在canvas显示的区域
                mNextDestRect.left = dis;

                canvas.drawBitmap(mCurBitmap, mNextSrcRect, mNextDestRect, null);
                canvas.drawBitmap(mNextBitmap, mSrcRect, mDestRect, null);
                break;
        }
    }

}