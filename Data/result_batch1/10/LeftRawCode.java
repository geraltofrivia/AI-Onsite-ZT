// https://github.com/software-mansion/react-native-svg/tree/be06e84ec4809a8071f18f9824ffbe61424ee80d/android/src/main/java/com/horcrux/svg/VirtualView.java#L578-L596
public class TempClass {
  protected void onLayout(boolean changed, int pleft, int ptop, int pright, int pbottom) {
    if (mClientRect == null) {
      return;
    }

    if (!(this instanceof GroupView)) {
      int left = (int) Math.floor(mClientRect.left);
      int top = (int) Math.floor(mClientRect.top);
      int right = (int) Math.ceil(mClientRect.right);
      int bottom = (int) Math.ceil(mClientRect.bottom);
      setLeft(left);
      setTop(top);
      setRight(right);
      setBottom(bottom);
    }
    int width = (int) Math.ceil(mClientRect.width());
    int height = (int) Math.ceil(mClientRect.height());
    setMeasuredDimension(width, height);
  }

}