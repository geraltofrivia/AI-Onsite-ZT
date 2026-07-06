// https://github.com/facebook/fresco/tree/febd5f3074ce2adb82fb15e81f2a329554a093c8/drawee/src/main/java/com/facebook/drawee/view/DraweeTransition.java#L85-L126
public class TempClass {
  @Override
  public @Nullable Animator createAnimator(
      ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
    if (startValues == null || endValues == null) {
      return null;
    }
    Rect startBounds = (Rect) startValues.values.get(PROPNAME_BOUNDS);
    Rect endBounds = (Rect) endValues.values.get(PROPNAME_BOUNDS);
    if (startBounds == null || endBounds == null) {
      return null;
    }
    if (mFromScale == mToScale && mFromFocusPoint == mToFocusPoint) {
      return null;
    }
    final GenericDraweeView draweeView = (GenericDraweeView) startValues.view;
    final ScalingUtils.InterpolatingScaleType scaleType =
        new ScalingUtils.InterpolatingScaleType(
            mFromScale, mToScale, startBounds, endBounds, mFromFocusPoint, mToFocusPoint);
    draweeView.getHierarchy().setActualImageScaleType(scaleType);

    ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
    animator.addUpdateListener(
        new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            float fraction = (float) animation.getAnimatedValue();
            scaleType.setValue(fraction);
          }
        });
    animator.addListener(
        new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            draweeView.getHierarchy().setActualImageScaleType(mToScale);
            if (mToFocusPoint != null) {
              draweeView.getHierarchy().setActualImageFocusPoint(mToFocusPoint);
            }
          }
        });

    return animator;
  }

}