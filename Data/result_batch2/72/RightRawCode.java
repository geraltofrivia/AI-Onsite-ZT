// https://github.com/k0shk0sh/FastHub/tree/fb7053da2079b624d6129ef81370a9d5774bcb9a/app/src/main/java/com/fastaccess/ui/modules/profile/org/teams/details/TeamPagerActivity.java#L68-L92
public class TempClass {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            id = getIntent().getExtras().getLong(BundleConstant.ID);
            name = getIntent().getExtras().getString(BundleConstant.EXTRA);
        }
        setTitle(name);
        if (id <= 0) {
            finish();
            return;
        }
        FragmentsPagerAdapter adapter = new FragmentsPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapterModel.buildForTeam(this, id));
        pager.setAdapter(adapter);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(pager);
        tabs.setPaddingRelative(0, 0, 0, 0);
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager) {
            @Override public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
                onScrollTop(tab.getPosition());
            }
        });
    }

}