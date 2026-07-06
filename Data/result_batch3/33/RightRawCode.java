// https://github.com/QuantumBadger/RedReader/tree/ea0b596c5107dfd997bbb6b84951ff86a05992de/src/main/java/org/quantumbadger/redreader/views/GIFView.java#L62-L86
public class TempClass {
	@Override
	protected void onDraw(final Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		super.onDraw(canvas);
		final long now = SystemClock.uptimeMillis();

		final float scale = Math.min(
				(float)getWidth() / mMovie.width(),
				(float)getHeight() / mMovie.height());

		canvas.scale(scale, scale);
		canvas.translate(
				((float)getWidth() / scale - (float)mMovie.width()) / 2f,
				((float)getHeight() / scale - (float)mMovie.height()) / 2f);


		if(movieStart == 0) {
			movieStart = (int)now;
		}

		mMovie.setTime((int)((now - movieStart) % mMovie.duration()));
		mMovie.draw(canvas, 0, 0, paint);

		this.invalidate();
	}

}