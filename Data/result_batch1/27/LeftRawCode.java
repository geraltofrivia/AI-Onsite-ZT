// https://github.com/zzhoujay/RichText/tree/4737a3ceada1135496441975df6371c17d3dd2c4/richtext/src/main/java/com/zzhoujay/richtext/drawable/DrawableWrapper.java#L51-L66
public class TempClass {
    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        if (drawable != null) {
            canvas.clipRect(getBounds());
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
            }
            drawImage(canvas);
        }
        drawBorder(canvas);
        canvas.restore();
    }

}