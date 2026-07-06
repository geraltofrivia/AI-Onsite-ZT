// https://github.com/mastodon/mastodon-android/tree/8f6ea2ea054105c31a06f16c018420d0cb3df94a/mastodon/src/main/java/org/joinmastodon/android/ui/drawables/AudioAttachmentBackgroundDrawable.java#L23-L51
public class TempClass {
	@Override
	public void draw(@NonNull Canvas canvas){
		Rect bounds=getBounds();
		paint.setColor(bgColor);
		canvas.drawRect(bounds, paint);

		float initialRadius=V.dp(48);
		float finalRadius=bounds.width()/2f;
		long time=SystemClock.uptimeMillis();
		boolean animationsStillRunning=false;

		for(int i=0;i<animationStartTimes.length;i++){
			 long t=time-animationStartTimes[i];
			 if(t<0)
				  continue;
			 float fraction=t/3000f;
			 if(fraction>1)
				  continue;
			 fraction=CubicBezierInterpolator.EASE_OUT.getInterpolation(fraction);
			 paint.setColor(wavesColor);
			 paint.setAlpha(Math.round(paint.getAlpha()*(1f-fraction)));
			 canvas.drawCircle(bounds.centerX(), bounds.centerY(), initialRadius+(finalRadius-initialRadius)*fraction, paint);
			 animationsStillRunning=true;
		}

		if(animationsStillRunning){
			 invalidateSelf();
		}
	}

}