// https://github.com/Piasy/BigImageViewer/tree/067f8eb86ee522190ab473f36a1b0de11c21a663/app/src/main/java/com/github/piasy/biv/example/ImageTypesActivity.java#L118-L143
public class TempClass {
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

}