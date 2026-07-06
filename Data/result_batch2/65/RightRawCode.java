// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/studio-android/LightNovelLibrary/app/src/main/java/org/mewx/wenku8/component/PagerSlidingTabStrip.java#L275-L298
public class TempClass {
    private void addTab(final int position, CharSequence title, View tabView) {
        TextView textView = (TextView) tabView.findViewById(R.id.tab_title);
        if (textView != null) {
            if (title != null) textView.setText(title);
            float alpha = pager.getCurrentItem() == position ? tabTextSelectedAlpha : tabTextAlpha;
            ViewCompat.setAlpha(textView, alpha);
        }

        tabView.setFocusable(true);
        tabView.setOnClickListener(new OnClickListener() {
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
        });

        tabsContainer.addView(tabView, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
    }

}