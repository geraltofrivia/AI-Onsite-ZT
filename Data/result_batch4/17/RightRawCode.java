// https://github.com/fossasia/phimpme-android/tree/60acbd2e5691d3d2f794b86e1277bfedab481035/app/src/main/java/org/fossasia/phimpme/editor/fragment/CropFragment.java#L206-L235
public class TempClass {
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    cancel = mainView.findViewById(R.id.crop_cancel);
    apply = mainView.findViewById(R.id.crop_apply);
    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      combinedList = mainView.findViewById(R.id.combinedList);
      setUpRatioListLandscape();
    } else if (getResources().getConfiguration().orientation
        == Configuration.ORIENTATION_PORTRAIT) {
      ratioList = mainView.findViewById(R.id.ratio_list_group);
      imageList = mainView.findViewById(R.id.image_crop);
      setUpRatioListPortriat();
    }
    this.mCropPanel = ensureEditActivity().mCropPanel;
    cancel.setOnClickListener(new BackToMenuClick());
    apply.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            applyCropImage();
          }
        });
    if (savedInstanceState != null) {
      rec = savedInstanceState.getParcelable("Rect");
      sView.setText(savedInstanceState.getString("text"));
    }
    onShow();
  }

}