// https://github.com/ybq/Android-SpinKit/tree/aa83e4d0a42c76eae15e8b18b79aa2a231cca5f8/library/src/main/java/com/github/ybq/android/spinkit/style/CubeGrid.java#L31-L45
public class TempClass {
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int width = (int) (bounds.width() * 0.33f);
        int height = (int) (bounds.height() * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int x = i % 3;
            int y = i / 3;
            int l = bounds.left + x * width;
            int t = bounds.top + y * height;
            Sprite sprite = getChildAt(i);
            sprite.setDrawBounds(l, t, l + width, t + height);
        }
    }

}