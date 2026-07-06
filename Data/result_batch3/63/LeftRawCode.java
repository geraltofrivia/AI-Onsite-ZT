// https://github.com/alibaba/Tangram-Android/tree/e8fe44d8aa0fd124afaefe945e65da3df9c9244c/tangram/src/main/java/com/tmall/wireless/tangram/BaseTangramEngine.java#L295-L327
public class TempClass {
    public void destroy() {
        if (mContentView != null) {
            if (mGroupBasicAdapter != null) {
                mGroupBasicAdapter.destroy();
            }
            mContentView.setAdapter(null);
            mContentView = null;
        }
        TimerSupport timerSupport = getService(TimerSupport.class);
        if (timerSupport != null) {
            timerSupport.clear();
        }
        SimpleClickSupport simpleClickSupport = getService(SimpleClickSupport.class);
        if (simpleClickSupport != null) {
            simpleClickSupport.destroy();
        }
        ExposureSupport exposureSupport = getService(ExposureSupport.class);
        if (exposureSupport != null) {
            exposureSupport.destroy();
        }
        BusSupport busSupport = getService(BusSupport.class);
        if (busSupport != null) {
            busSupport.shutdown();
        }
        BannerSupport bannerSupport = getService(BannerSupport.class);
        if (bannerSupport != null) {
            bannerSupport.destroy();
        }
        VafContext vafContext = getService(VafContext.class);
        if (vafContext != null) {
            vafContext.onDestroy();
        }
    }

}