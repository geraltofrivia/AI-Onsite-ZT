// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/studio-android/LightNovelLibrary/app/src/main/java/org/mewx/wenku8/component/PagerSlidingTabStrip.java#L285-L294
public class TempClass {
            @Override
            public void onClick(View v) {
                if (pager.getCurrentItem() != position) {
                    View tab = tabsContainer.getChildAt(pager.getCurrentItem());
                    notSelected(tab);
                    pager.setCurrentItem(position);
                } else if (tabReselectedListener != null) {
                    tabReselectedListener.onTabReselected(position);
                }
            }

}