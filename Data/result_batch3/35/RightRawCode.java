// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/fragments/list/channel/ChannelFragment.java#L198-L212
public class TempClass {
    @Override // called from onViewCreated in BaseFragment.onViewCreated
    protected void initViews(final View rootView, final Bundle savedInstanceState) {
        super.initViews(rootView, savedInstanceState);

        tabAdapter = new TabAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(tabAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        setTitle(name);
        binding.channelTitleView.setText(name);
        if (!ImageStrategy.shouldLoadImages()) {
            // do not waste space for the banner if it is not going to be loaded
            binding.channelBannerImage.setImageDrawable(null);
        }
    }

}