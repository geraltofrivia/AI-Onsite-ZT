// https://github.com/aporter/coursera-android/tree/157373885fbfa18b83fa97cd46f6a003905970ea/Examples/TouchGestures/src/course/examples/touch/gestures/GesturesActivity.java#L31-L53
public class TempClass {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mFrame = (FrameLayout) findViewById(R.id.frame);
		mBgColor = new Random().nextInt(0xFFFFFF) | 0xFF000000;
		mFirstColor = mBgColor;
		mFrame.setBackgroundColor(mBgColor);

		mLayout = (RelativeLayout) findViewById(R.id.main);
		mLayout.setBackgroundColor(mStartBgColor);

		mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!mLibrary.load()) {
			finish();
		}

		// Make this the target of gesture detection callbacks
		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestures_overlay);
		gestureView.addOnGesturePerformedListener(this);

	}

}