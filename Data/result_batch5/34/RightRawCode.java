// https://github.com/gedoor/MyBookshelf/tree/bb5a99058f387dc08d727cfe1418d294a53a9f48/app/src/main/java/com/kunfei/bookshelf/widget/check_box/SmoothCheckBox.java#L79-L125
public class TempClass {
    private void init(Context context, AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SmoothCheckBox);
        int tickColor = ThemeStore.accentColor(context);
        mCheckedColor = context.getResources().getColor(R.color.background_card);
        mUnCheckedColor = context.getResources().getColor(R.color.background_menu);
        mFloorColor = context.getResources().getColor(R.color.transparent30);
        tickColor = ta.getColor(R.styleable.SmoothCheckBox_color_tick, tickColor);
        mAnimDuration = ta.getInt(R.styleable.SmoothCheckBox_duration, DEF_ANIM_DURATION);
        mFloorColor = ta.getColor(R.styleable.SmoothCheckBox_color_unchecked_stroke, mFloorColor);
        mCheckedColor = ta.getColor(R.styleable.SmoothCheckBox_color_checked, mCheckedColor);
        mUnCheckedColor = ta.getColor(R.styleable.SmoothCheckBox_color_unchecked, mUnCheckedColor);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.SmoothCheckBox_stroke_width, DensityUtil.dp2px(getContext(), 0));
        ta.recycle();

        mFloorUnCheckedColor = mFloorColor;
        mTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTickPaint.setStyle(Paint.Style.STROKE);
        mTickPaint.setStrokeCap(Paint.Cap.ROUND);
        mTickPaint.setColor(tickColor);

        mFloorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFloorPaint.setStyle(Paint.Style.FILL);
        mFloorPaint.setColor(mFloorColor);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCheckedColor);

        mTickPath = new Path();
        mCenterPoint = new Point();
        mTickPoints = new Point[3];
        mTickPoints[0] = new Point();
        mTickPoints[1] = new Point();
        mTickPoints[2] = new Point();

        setOnClickListener(v -> {
            toggle();
            mTickDrawing = false;
            mDrewDistance = 0;
            if (isChecked()) {
                startCheckedAnimation();
            } else {
                startUnCheckedAnimation();
            }
        });
    }

}