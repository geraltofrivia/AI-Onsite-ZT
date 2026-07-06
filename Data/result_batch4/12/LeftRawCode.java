// https://github.com/jpush/aurora-imui/tree/fb74b79288d38b8817ca24abced7bb4bc9b18c4b/Android/sample/exampleui/src/main/java/imui/jiguang/cn/imuisample/views/photoview/VersionedGestureDetector.java#L32-L47
public class TempClass {
	public static VersionedGestureDetector newInstance(Context context, OnGestureListener listener) {
		final int sdkVersion = Build.VERSION.SDK_INT;
		VersionedGestureDetector detector = null;

		if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
			detector = new CupcakeDetector(context);
		} else if (sdkVersion < Build.VERSION_CODES.FROYO) {
			detector = new EclairDetector(context);
		} else {
			detector = new FroyoDetector(context);
		}

		detector.mListener = listener;

		return detector;
	}

}