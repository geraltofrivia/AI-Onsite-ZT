// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/ui/settings/pages/AppearanceSettingsFragment.java#L72-L130
public class TempClass {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (activity == null) {
            activity = (AppCompatActivity) requireActivity();
        }

        pref = RepositoryHelper.getSettingsRepository(activity.getApplicationContext());

        String keyTheme = getString(R.string.pref_key_theme);
        ListPreference theme = findPreference(keyTheme);
        if (theme != null) {
            var themeVal = pref.theme();
            theme.setValueIndex(themeVal.getId());
            bindOnPreferenceChangeListener(theme);
        }

        String keyDynamicColors = getString(R.string.pref_key_theme_dynamic_colors);
        SwitchPreferenceCompat dynamicColors = findPreference(keyDynamicColors);
        if (dynamicColors != null) {
            dynamicColors.setChecked(pref.dynamicColors());
            bindOnPreferenceChangeListener(dynamicColors);
        }

        String keyTorrentFinishNotify = getString(R.string.pref_key_torrent_finish_notify);
        SwitchPreferenceCompat torrentFinishNotify = findPreference(keyTorrentFinishNotify);
        if (torrentFinishNotify != null) {
            torrentFinishNotify.setChecked(pref.torrentFinishNotify());
            bindOnPreferenceChangeListener(torrentFinishNotify);
        }

        String keyForegroundNotifyStatusFilter = getString(R.string.pref_key_foreground_notify_status_filter);
        ListPreference foregroundNotifyStatusFilter = findPreference(keyForegroundNotifyStatusFilter);
        if (foregroundNotifyStatusFilter != null) {
            int type = Integer.parseInt(pref.foregroundNotifyStatusFilter());
            foregroundNotifyStatusFilter.setValueIndex(type);
            bindOnPreferenceChangeListener(foregroundNotifyStatusFilter);
        }

        String keyForegroundNotifySorting = getString(R.string.pref_key_foreground_notify_sorting);
        ListPreference foregroundNotifySorting = findPreference(keyForegroundNotifySorting);
        if (foregroundNotifySorting != null) {
            int type = Integer.parseInt(pref.foregroundNotifySorting());
            foregroundNotifySorting.setValueIndex(type);
            bindOnPreferenceChangeListener(foregroundNotifySorting);
        }

        String keyCombinedPauseButton = getString(R.string.pref_key_foreground_notify_combined_pause_button);
        SwitchPreferenceCompat combinedPauseButton = findPreference(keyCombinedPauseButton);
        if (combinedPauseButton != null) {
            combinedPauseButton.setChecked(pref.foregroundNotifyCombinedPauseButton());
            bindOnPreferenceChangeListener(combinedPauseButton);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            initLegacyNotifySettings(pref);
        }
    }

}