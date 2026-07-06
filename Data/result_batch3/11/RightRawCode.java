// https://github.com/MiPushFramework/MiPushFramework/tree/1425051fdf5dc20a5d4c6bed2dd821ccd5123acc/push/src/main/java/android/support/design/internal/TextScale.java#L53-L82
public class TempClass {
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
            TransitionValues endValues) {
        if (startValues == null || endValues == null || !(startValues.view instanceof TextView)
                || !(endValues.view instanceof TextView)) {
            return null;
        }
        final TextView view = (TextView) endValues.view;
        Map<String, Object> startVals = startValues.values;
        Map<String, Object> endVals = endValues.values;
        final float startSize = startVals.get(PROPNAME_SCALE) != null ? (float) startVals.get(
                PROPNAME_SCALE) : 1f;
        final float endSize = endVals.get(PROPNAME_SCALE) != null ? (float) endVals.get(
                PROPNAME_SCALE) : 1f;
        if (startSize == endSize) {
            return null;
        }

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                view.setScaleX(animatedValue);
                view.setScaleY(animatedValue);
            }
        });
        return animator;
    }

}