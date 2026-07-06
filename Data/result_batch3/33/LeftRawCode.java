// https://github.com/zzhoujay/RichText/tree/4737a3ceada1135496441975df6371c17d3dd2c4/richtext/src/main/java/com/zzhoujay/richtext/drawable/GifDrawable.java#L55-L72
public class TempClass {
    @Override
    public void draw(@NonNull Canvas canvas) {
        long now = android.os.SystemClock.uptimeMillis();
        if (start == 0) { // first time
            start = now;
        }
        if (movie != null) {
            int dur = movie.duration();
            if (dur == 0) {
                dur = 1000;
            }
            int relTime = (int) ((now - start) % dur);
            movie.setTime(relTime);
            Rect bounds = getBounds();
            canvas.scale(scaleX, scaleY);
            movie.draw(canvas, bounds.left, bounds.top);
        }
    }

}