// https://github.com/facebook/fresco/tree/febd5f3074ce2adb82fb15e81f2a329554a093c8/samples/showcase/src/main/java/com/facebook/fresco/samples/showcase/vito/FrescoVitoLithoRegionDecodeFragment.java#L80-L107
public class TempClass {
  @Override
  public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
    mUri =
        sampleUris()
            .createSampleUri(ImageUriProvider.ImageSize.L, ImageUriProvider.Orientation.LANDSCAPE);

    mFullImageView = (ImageViewWithAspectRatio) view.findViewById(R.id.image_full);
    mFullImageView.setAspectRatio(2f);
    VitoView.show(
        mUri, IMAGE_OPTIONS, "FrescoVitoLithoRegionDecodeFragment", mImageListener, mFullImageView);

    mSelectedParentBounds = (ResizableFrameLayout) view.findViewById(R.id.frame_parent_bounds);
    mSelectedParentBounds.init(view.findViewById(R.id.btn_resize_parent_bounds));
    mSelectedParentBounds.setSizeChangedListener(mSizeChangedListener);

    mSelectedFocusPoint = (ResizableFrameLayout) view.findViewById(R.id.frame_focus_point);
    mSelectedFocusPoint.init(view.findViewById(R.id.btn_resize_focus_point));
    mSelectedFocusPoint.setSizeChangedListener(mSizeChangedListener);

    mRegionImageView = (ImageView) view.findViewById(R.id.image_region);
    mRegionImageView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            updateRegion();
          }
        });
  }

}