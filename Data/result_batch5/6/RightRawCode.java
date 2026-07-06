// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/popup/ArrowPopup.java#L667-L706
public class TempClass {
    protected void animateClose() {
        if (!mIsOpen) {
            return;
        }
        if (mOpenCloseAnimator != null) {
            mOpenCloseAnimator.cancel();
        }
        mIsOpen = false;

        mOpenCloseAnimator = ENABLE_MATERIAL_U_POPUP.get()
                ? getMaterialUOpenCloseAnimator(
                        false,
                        CLOSE_DURATION_U,
                        CLOSE_FADE_START_DELAY_U,
                        CLOSE_FADE_DURATION_U,
                        CLOSE_CHILD_FADE_START_DELAY_U,
                        CLOSE_CHILD_FADE_DURATION_U,
                        EMPHASIZED_ACCELERATE)
                : getOpenCloseAnimator(false,
                        mCloseDuration,
                        mCloseFadeStartDelay,
                        mCloseFadeDuration,
                        mCloseChildFadeStartDelay,
                        mCloseChildFadeDuration,
                        ACCELERATED_EASE);

        onCreateCloseAnimation(mOpenCloseAnimator);
        mOpenCloseAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mOpenCloseAnimator = null;
                if (mDeferContainerRemoval) {
                    setVisibility(INVISIBLE);
                } else {
                    closeComplete();
                }
            }
        });
        mOpenCloseAnimator.start();
    }

}