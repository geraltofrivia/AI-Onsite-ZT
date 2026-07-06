// https://github.com/ybq/Android-SpinKit/tree/aa83e4d0a42c76eae15e8b18b79aa2a231cca5f8/sample/src/main/java/com/github/ybq/android/loading/MainActivity.java#L17-L50
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] titles = new String[]{
                    "Style", "Widget"
            };

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return StyleFragment.newInstance();
                } else {
                    return WidgetFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

}