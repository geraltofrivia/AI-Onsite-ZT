// https://github.com/lipangit/JiaoZiVideoPlayer/tree/3db479b1a816965336fa5884708037a0a2971c33/jiaozivideoplayer/src/main/java/cn/jzvd/JzvdStd.java#L305-L321
public class TempClass {
    @Override
    public void setScreenFullscreen() {
        super.setScreenFullscreen();
        //进入全屏之后要保证原来的播放状态和ui状态不变，改变个别的ui
        fullscreenButton.setImageResource(R.drawable.jz_shrink);
        backButton.setVisibility(View.VISIBLE);
        tinyBackImageView.setVisibility(View.INVISIBLE);
        batteryTimeLayout.setVisibility(View.VISIBLE);
        if (jzDataSource.urlsMap.size() == 1) {
            clarity.setVisibility(GONE);
        } else {
            clarity.setText(jzDataSource.getCurrentKey().toString());
            clarity.setVisibility(View.VISIBLE);
        }
        changeStartButtonSize((int) getResources().getDimension(R.dimen.jz_start_button_w_h_fullscreen));
        setSystemTimeAndBattery();
    }

}