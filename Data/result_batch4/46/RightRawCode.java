// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/fragments/list/channel/ChannelFragment.java#L469-L507
public class TempClass {
    private void updateTabs() {
        tabAdapter.clearAllItems();

        if (currentInfo != null && !channelContentNotSupported) {
            final Context context = requireContext();
            final SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(context);

            for (final ListLinkHandler linkHandler : currentInfo.getTabs()) {
                final String tab = linkHandler.getContentFilters().get(0);
                if (ChannelTabHelper.showChannelTab(context, preferences, tab)) {
                    final ChannelTabFragment channelTabFragment =
                            ChannelTabFragment.getInstance(serviceId, linkHandler, name);
                    channelTabFragment.useAsFrontPage(useAsFrontPage);
                    tabAdapter.addFragment(channelTabFragment,
                            context.getString(ChannelTabHelper.getTranslationKey(tab)));
                }
            }

            if (ChannelTabHelper.showChannelTab(
                    context, preferences, R.string.show_channel_tabs_about)) {
                tabAdapter.addFragment(
                        new ChannelAboutFragment(currentInfo),
                        context.getString(R.string.channel_tab_about));
            }
        }

        tabAdapter.notifyDataSetUpdate();

        for (int i = 0; i < tabAdapter.getCount(); i++) {
            binding.tabLayout.getTabAt(i).setText(tabAdapter.getItemTitle(i));
        }

        // Restore previously selected tab
        final TabLayout.Tab ltab = binding.tabLayout.getTabAt(lastTab);
        if (ltab != null) {
            binding.tabLayout.selectTab(ltab);
        }
    }

}