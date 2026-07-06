// https://github.com/alibaba/UltraViewPager/tree/b65233e6d63f062345229d5eef9326136bde5eed/ultraviewpager/src/main/java/com/tmall/ultraviewpager/UltraViewPagerView.java#L191-L206
public class TempClass {
    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            if (pagerAdapter == null || pagerAdapter.getAdapter() != adapter) {
                pagerAdapter = new UltraViewPagerAdapter(adapter);
                pagerAdapter.setCenterListener(this);
                pagerAdapter.setEnableLoop(enableLoop);
                pagerAdapter.setMultiScrRatio(multiScrRatio);
                needsMeasurePage = true;
                constrainLength = 0;
                super.setAdapter(pagerAdapter);
            }
        } else {
            super.setAdapter(adapter);
        }
    }

}