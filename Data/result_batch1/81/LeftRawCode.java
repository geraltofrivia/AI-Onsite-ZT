// https://github.com/software-mansion/react-native-svg/tree/be06e84ec4809a8071f18f9824ffbe61424ee80d/android/src/main/java/com/horcrux/svg/SvgView.java#L91-L112
public class TempClass {
  @Override
  public void invalidate() {
    super.invalidate();
    ViewParent parent = getParent();
    if (parent instanceof VirtualView) {
      if (!mRendered) {
        return;
      }
      mRendered = false;
      ((VirtualView) parent).getSvgView().invalidate();
      return;
    }
    if (!mRemovalTransitionStarted) {
      // when view is removed from the view hierarchy, we want to recycle the mBitmap when
      // the view is detached from window, in order to preserve it for during animation, see
      // https://github.com/react-native-svg/react-native-svg/pull/1542
      if (mBitmap != null) {
        mBitmap.recycle();
      }
      mBitmap = null;
    }
  }

}