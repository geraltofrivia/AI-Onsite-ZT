// https://github.com/geeeeeeeeek/WeChatLuckyMoney/tree/54a5201dfba6819f476a61e0e8096f13e3037ed9/app/src/main/java/xyz/monkeytong/hongbao/activities/SeekBarPreference.java#L20-L36
public class TempClass {
    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.preference_seekbar);

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attr = attrs.getAttributeName(i);
            if (attr.equalsIgnoreCase("pref_kind")) {
                prefKind = attrs.getAttributeValue(i);
                break;
            }
        }
        if (prefKind.equals("pref_open_delay")) {
            hintText = getContext().getString(R.string.delay_open);
        } else if (prefKind.equals("pref_comment_delay")) {
            hintText = "发送回复(暂不支持延时)";
        }
    }

}