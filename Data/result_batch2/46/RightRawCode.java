// https://github.com/gedoor/MyBookshelf/tree/bb5a99058f387dc08d727cfe1418d294a53a9f48/app/src/main/java/com/kunfei/bookshelf/widget/recycler/refresh/RefreshProgressBar.java#L81-L138
public class TempClass {
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(bgColor);
        bgRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRect(bgRect, paint);

        if (secondDurProgress > 0 && secondMaxProgress > 0) {
            int secondDur = secondDurProgress;
            if (secondDur > secondMaxProgress) {
                secondDur = secondMaxProgress;
            }
            paint.setColor(secondColor);
            int tempW = (int) (getMeasuredWidth() * 1.0f * (secondDur * 1.0f / secondMaxProgress));
            secondRect.set(getMeasuredWidth() / 2 - tempW / 2, 0, getMeasuredWidth() / 2 + tempW / 2, getMeasuredHeight());
            canvas.drawRect(secondRect, paint);
        }

        if (durProgress > 0 && maxProgress > 0) {
            paint.setColor(fontColor);
            fontRectF.set(0, 0, getMeasuredWidth() * 1.0f * (durProgress * 1.0f / maxProgress), getMeasuredHeight());
            canvas.drawRect(fontRectF, paint);
        }

        if (isAutoLoading) {
            if (secondDurProgress >= secondMaxProgress) {
                a = -1;
            } else if (secondDurProgress <= 0) {
                a = 1;
            }
            secondDurProgress += (a * speed);
            if (secondDurProgress < 0)
                secondDurProgress = 0;
            else if (secondDurProgress > secondMaxProgress)
                secondDurProgress = secondMaxProgress;
            secondFinalProgress = secondDurProgress;
            invalidate();
        } else {
            if (secondDurProgress != secondFinalProgress) {
                if (secondDurProgress > secondFinalProgress) {
                    secondDurProgress -= speed;
                    if (secondDurProgress < secondFinalProgress) {
                        secondDurProgress = secondFinalProgress;
                    }
                } else {
                    secondDurProgress += speed;
                    if (secondDurProgress > secondFinalProgress) {
                        secondDurProgress = secondFinalProgress;
                    }
                }
                this.invalidate();
            }
            if (secondDurProgress == 0 && durProgress == 0 && secondFinalProgress == 0 && getVisibility() == View.VISIBLE) {
                setVisibility(View.INVISIBLE);
            }
        }
    }

}