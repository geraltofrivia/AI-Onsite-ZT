// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/me/imid/swipebacklayout/lib/SwipeBackLayout.java#L604-L613
public class TempClass {
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			int ret = 0;
			if ((mTrackingEdge & EDGE_LEFT) != 0) {
				ret = Math.min(child.getWidth(), Math.max(left, 0));
			} else if ((mTrackingEdge & EDGE_RIGHT) != 0) {
				ret = Math.min(0, Math.max(left, -child.getWidth()));
			}
			return ret;
		}

}