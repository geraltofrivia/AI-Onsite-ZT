// https://github.com/beemdevelopment/Aegis/tree/aa4877607dadf86c88c2f5a6cc3de6c869ff1132/app/src/main/java/com/beemdevelopment/aegis/helpers/FabScrollHelper.java#L26-L56
public class TempClass {
    public void setVisible(boolean visible) {
        if (visible) {
            _fabMenu.setVisibility(View.VISIBLE);
            _fabMenu.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(2))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            _isAnimating = false;
                            super.onAnimationEnd(animation);
                        }
                    }).start();
        } else {
            _isAnimating = true;
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) _fabMenu.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            _fabMenu.animate()
                    .translationY(_fabMenu.getHeight() + fabBottomMargin)
                    .setInterpolator(new AccelerateInterpolator(2))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            _isAnimating = false;
                            _fabMenu.setVisibility(View.INVISIBLE);
                            super.onAnimationEnd(animation);
                        }
                    }).start();
        }

    }

}