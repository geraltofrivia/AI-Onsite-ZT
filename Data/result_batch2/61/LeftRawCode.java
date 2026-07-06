// https://github.com/geeeeeeeeek/WeChatLuckyMoney/tree/54a5201dfba6819f476a61e0e8096f13e3037ed9/app/src/main/java/xyz/monkeytong/hongbao/activities/SeekBarPreference.java#L38-L71
public class TempClass {
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        SharedPreferences pref = getSharedPreferences();

        int delay = pref.getInt(prefKind, 0);
        this.seekBar = (SeekBar) view.findViewById(R.id.delay_seekBar);
        this.seekBar.setProgress(delay);

        if (prefKind.equals("pref_comment_delay")) {
            this.seekBar.setEnabled(false);
        }

        this.textView = (TextView) view.findViewById(R.id.pref_seekbar_textview);
        setHintText(0);

        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setHintText(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}