// https://github.com/mastodon/mastodon-android/tree/8f6ea2ea054105c31a06f16c018420d0cb3df94a/mastodon/src/main/java/org/joinmastodon/android/ui/views/ComposeMediaLayout.java#L70-L96
public class TempClass {
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b){
		int gap=V.dp(GAP_DP);
		int width=r-l;
		int height=b-t;
		int halfWidth=(width-gap)/2;
		int halfHeight=(height-gap)/2;
		switch(getChildCount()){
			case 0 -> {}
			case 1 -> getChildAt(0).layout(0, 0, width, height);
			case 2 -> {
				getChildAt(0).layout(0, 0, halfWidth, height);
				getChildAt(1).layout(halfWidth+gap, 0, width, height);
			}
			case 3 -> {
				getChildAt(0).layout(0, 0, halfWidth, height);
				getChildAt(1).layout(halfWidth+gap, 0, width, halfHeight);
				getChildAt(2).layout(halfWidth+gap, halfHeight+gap, width, height);
			}
			default -> {
				getChildAt(0).layout(0, 0, halfWidth, halfHeight);
				getChildAt(1).layout(halfWidth+gap, 0, width, halfHeight);
				getChildAt(2).layout(0, halfHeight+gap, halfWidth, height);
				getChildAt(3).layout(halfWidth+gap, halfHeight+gap, width, height);
			}
		}
	}

}