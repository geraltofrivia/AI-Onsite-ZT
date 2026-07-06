// https://github.com/gedoor/MyBookshelf/tree/bb5a99058f387dc08d727cfe1418d294a53a9f48/app/src/main/java/com/kunfei/bookshelf/widget/page/animation/SimulationPageAnim.java#L134-L171
public class TempClass {
                dx = (int) (mScreenWidth - mTouchX);
            } else {
                dx = -(int) mTouchX;
            }

            if (!mDirection.equals(Direction.NEXT)) {
                dx = (int) -(mScreenWidth + mTouchX);
            }

            if (mCornerY > 0) {
                dy = (int) (mScreenHeight - mTouchY);
            } else {
                dy = -(int) mTouchY; // 防止mTouchY最终变为0
            }
        } else {
            if (mCornerX > 0 && mDirection.equals(Direction.NEXT)) {
                dx = -(int) (mScreenWidth + mTouchX);
            } else {
                dx = (int) (mScreenWidth - mTouchX + mScreenWidth);
            }
            if (mCornerY > 0) {
                dy = (int) (mScreenHeight - mTouchY);
            } else {
                dy = (int) (1 - mTouchY); // 防止mTouchY最终变为0
            }
        }
        mScroller.startScroll((int) mTouchX, (int) mTouchY, dx, dy, animationSpeed);
        super.startAnim();
    }

    @Override
    public void drawMove(Canvas canvas) {
        if (mDirection == Direction.NEXT) {
            calcPoints();
            drawCurrentPageArea(canvas, bitmapList.get(1));//绘制翻页时的正面页
            drawNextPageAreaAndShadow(canvas, bitmapList.get(2));
            drawCurrentPageShadow(canvas);
            drawCurrentBackArea(canvas, bitmapList.get(1));

}