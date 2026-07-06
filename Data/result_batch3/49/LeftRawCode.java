// https://github.com/geeeeeeeeek/WeChatLuckyMoney/tree/54a5201dfba6819f476a61e0e8096f13e3037ed9/app/src/main/java/xyz/monkeytong/hongbao/fragments/CommentSettingsFragment.java#L22-L46
public class TempClass {
    private void setPrefListeners() {
        Preference updatePref = findPreference("pref_comment_switch");
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            updatePref.setEnabled(false);
        }
        Toast.makeText(getActivity(), "该功能尚处于实验中,只能自动填充感谢语,无法直接发送.", Toast.LENGTH_LONG).show();

        Preference commentWordsPref = findPreference("pref_comment_words");
        String summary = getResources().getString(R.string.pref_comment_words_summary);
        String value = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("pref_comment_words", "");
        if (value.length() > 0) commentWordsPref.setSummary(summary + ":" + value);

        commentWordsPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                String summary = getResources().getString(R.string.pref_comment_words_summary);
                if (o != null && o.toString().length() > 0) {
                    preference.setSummary(summary + ":" + o.toString());
                } else {
                    preference.setSummary(summary);
                }
                return true;
            }
        });
    }

}