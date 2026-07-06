// https://github.com/mastodon/mastodon-android/tree/8f6ea2ea054105c31a06f16c018420d0cb3df94a/mastodon/src/main/java/org/joinmastodon/android/ui/drawables/TiledDrawable.java#L18-L32
public class TempClass {
	@Override
	public void draw(@NonNull Canvas canvas){
		Rect bounds=getBounds();
		canvas.save();
		canvas.clipRect(bounds);
		int w=drawable.getIntrinsicWidth();
		int h=drawable.getIntrinsicHeight();
		for(int y=bounds.top;y<bounds.bottom;y+=h){
			for(int x=bounds.left;x<bounds.right;x+=w){
				drawable.setBounds(x, y, x+w, y+h);
				drawable.draw(canvas);
			}
		}
		canvas.restore();
	}

}