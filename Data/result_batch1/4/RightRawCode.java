// https://github.com/QuantumBadger/RedReader/tree/ea0b596c5107dfd997bbb6b84951ff86a05992de/src/main/java/org/quantumbadger/redreader/activities/ImgurUploadActivity.java#L77-L157
public class TempClass {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		PrefsUtility.applyTheme(this);

		super.onCreate(savedInstanceState);

		setTitle(R.string.upload_to_imgur);

		final FrameLayout outerLayout = new FrameLayout(this);

		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		mTextView = new TextView(this);
		mTextView.setText(R.string.no_file_selected);
		layout.addView(mTextView);

		General.setAllMarginsDp(this, mTextView, 10);

		mUploadButton = new Button(this);
		mUploadButton.setText(R.string.button_upload);
		mUploadButton.setEnabled(false);
		layout.addView(mUploadButton);
		updateUploadButtonVisibility();

		final Button browseButton = new Button(this);
		browseButton.setText(R.string.button_browse);
		layout.addView(browseButton);

		mThumbnailView = new ImageView(this);
		layout.addView(mThumbnailView);
		General.setAllMarginsDp(this, mThumbnailView, 20);

		browseButton.setOnClickListener(v -> {
			final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			startActivityForResultWithCallback(intent, (resultCode, data) -> {

				if(data == null || data.getData() == null) {
					return;
				}

				if(resultCode != RESULT_OK) {
					return;
				}

				onImageSelected(data.getData());
			});
		});

		mUploadButton.setOnClickListener(v -> {
			if(mImageData != null) {
				uploadImage();
			} else {
				General.quickToast(this, R.string.no_file_selected);
			}
		});

		final ScrollView sv = new ScrollView(this);
		sv.addView(layout);
		outerLayout.addView(sv);

		{
			mLoadingOverlay = new LoadingSpinnerView(this);
			outerLayout.addView(mLoadingOverlay);
			mLoadingOverlay.setBackgroundColor(Color.argb(220, 50, 50, 50));

			General.setLayoutMatchParent(mLoadingOverlay);

			mLoadingOverlay.setOnClickListener(v -> {
				// Do nothing
			});

			mLoadingOverlay.setVisibility(View.GONE);
		}

		setBaseActivityListing(outerLayout);

		General.setAllMarginsDp(this, layout, 20);
	}

}