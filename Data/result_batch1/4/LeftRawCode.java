// https://github.com/Piasy/BigImageViewer/tree/067f8eb86ee522190ab473f36a1b0de11c21a663/app/src/main/java/com/github/piasy/biv/example/ImageTypesActivity.java#L82-L145
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_type);

        mRootLayout = findViewById(R.id.rootLayout);

        Spinner spLoader = findViewById(R.id.spLoader);
        spLoader.setAdapter(getArrayAdapter(IMAGE_LOADERS));
        spLoader.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                    final int position, final long id) {
                mImageLoader = IMAGE_LOADERS.get(position).value;
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });

        Spinner spType = findViewById(R.id.spType);
        spType.setAdapter(getArrayAdapter(IMAGE_TYPES));
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                    final int position, final long id) {
                mImageType = IMAGE_TYPES.get(position).value;
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mBiv != null) {
                    mRootLayout.removeView(mBiv);
                }
                ImageViewFactory imageViewFactory;
                switch (mImageLoader) {
                    case IMAGE_LOADER_FRESCO:
                        BigImageViewer.initialize(FrescoImageLoader.with(getApplicationContext()));
                        imageViewFactory = new FrescoImageViewFactory();
                        break;
                    case IMAGE_LOADER_GLIDE:
                        BigImageViewer.initialize(GlideImageLoader.with(getApplicationContext()));
                        imageViewFactory = new GlideImageViewFactory();
                        break;
                    default:
                        return;
                }
                mBiv = new BigImageView(ImageTypesActivity.this);
                mBiv.setImageViewFactory(imageViewFactory);
                mBiv.setProgressIndicator(new ProgressPieIndicator());
                mRootLayout.addView(mBiv,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                mBiv.showImage(Uri.parse(IMAGE_URLS.get(mImageType)));
            }
        });
    }

}