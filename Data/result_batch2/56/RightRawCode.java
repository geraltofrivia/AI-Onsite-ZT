// https://github.com/WangDaYeeeeee/GeometricWeather/tree/b2373551ea97232176afd8cd82dd75f1ef53c8b8/app/src/main/java/wangdaye/com/geometricweather/common/ui/widgets/NumberAnimTextView.java#L129-L159
public class TempClass {
            setText(mPrefixString + format(new BigDecimal(mNumEnd)) + mPostfixString);
            return;
        }
        BidiFormatter f = BidiFormatter.getInstance();

        animator = ValueAnimator.ofObject(new BigDecimalEvaluator(), new BigDecimal(mNumStart), new BigDecimal(mNumEnd));
        animator.setDuration(mDuration);
        animator.setInterpolator(new DecelerateInterpolator(3f));
        animator.addUpdateListener(valueAnimator -> {
            BigDecimal value = (BigDecimal) valueAnimator.getAnimatedValue();
            setText(mPrefixString + f.unicodeWrap(format(value)) + mPostfixString);
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setText(mPrefixString + f.unicodeWrap(mNumEnd) + mPostfixString);
            }
        });
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }

    /**
     * 格式化 BigDecimal ,小数部分时保留两位小数并四舍五入

}