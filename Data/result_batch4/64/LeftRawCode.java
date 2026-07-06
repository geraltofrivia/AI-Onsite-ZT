// https://github.com/H07000223/FlycoTabLayout/tree/528fcffe88d54eeafbc85ffd1ce375df043b3a28/FlycoTabLayout_Lib/src/main/java/com/flyco/tablayout/SlidingTabLayout.java#L261-L281
public class TempClass {
            @Override
            public void onClick(View v) {
                int position = mTabsContainer.indexOfChild(v);
                if (position != -1) {
                    if (mViewPager.getCurrentItem() != position) {
                        if (mSnapOnTabClick) {
                            mViewPager.setCurrentItem(position, false);
                        } else {
                            mViewPager.setCurrentItem(position);
                        }

                        if (mListener != null) {
                            mListener.onTabSelect(position);
                        }
                    } else {
                        if (mListener != null) {
                            mListener.onTabReselect(position);
                        }
                    }
                }
            }

}