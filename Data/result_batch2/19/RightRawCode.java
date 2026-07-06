// https://github.com/android-hacker/VirtualXposed/tree/122beb371519cb2d221ce06756361aaa30e2674f/VirtualApp/app/src/main/java/io/virtualapp/widgets/LabelView.java#L42-L55
public class TempClass {
    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mTextContent = ta.getString(R.styleable.LabelView_lv_text);
        mTextColor = ta.getColor(R.styleable.LabelView_lv_text_color, Color.parseColor("#ffffff"));
        mTextSize = ta.getDimension(R.styleable.LabelView_lv_text_size, sp2px(11));
        mTextBold = ta.getBoolean(R.styleable.LabelView_lv_text_bold, true);
        mTextAllCaps = ta.getBoolean(R.styleable.LabelView_lv_text_all_caps, true);
        mFillTriangle = ta.getBoolean(R.styleable.LabelView_lv_fill_triangle, false);
        mBackgroundColor = ta.getColor(R.styleable.LabelView_lv_background_color, Color.parseColor("#FF4081"));
        mMinSize = ta.getDimension(R.styleable.LabelView_lv_min_size, mFillTriangle ? dp2px(35) : dp2px(50));
        mPadding = ta.getDimension(R.styleable.LabelView_lv_padding, dp2px(3.5f));
        mGravity = ta.getInt(R.styleable.LabelView_lv_gravity, Gravity.TOP | Gravity.LEFT);
        ta.recycle();
    }

}