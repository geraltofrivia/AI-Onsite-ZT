// https://github.com/ChadCSong/ShineButton/tree/428893c44acefb168f91574f2077e461e0eff3ea/shinebuttonlib/src/main/java/com/sackcentury/shinebuttonlib/ShineButton.java#L73-L94
public class TempClass {
    private void initButton(Context context, AttributeSet attrs) {

        if (context instanceof Activity) {
            init((Activity) context);
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShineButton);
        btnColor = a.getColor(R.styleable.ShineButton_btn_color, Color.GRAY);
        btnFillColor = a.getColor(R.styleable.ShineButton_btn_fill_color, Color.BLACK);
        shineParams.allowRandomColor = a.getBoolean(R.styleable.ShineButton_allow_random_color, false);
        shineParams.animDuration = a.getInteger(R.styleable.ShineButton_shine_animation_duration, (int) shineParams.animDuration);
        shineParams.bigShineColor = a.getColor(R.styleable.ShineButton_big_shine_color, shineParams.bigShineColor);
        shineParams.clickAnimDuration = a.getInteger(R.styleable.ShineButton_click_animation_duration, (int) shineParams.clickAnimDuration);
        shineParams.enableFlashing = a.getBoolean(R.styleable.ShineButton_enable_flashing, false);
        shineParams.shineCount = a.getInteger(R.styleable.ShineButton_shine_count, shineParams.shineCount);
        shineParams.shineDistanceMultiple = a.getFloat(R.styleable.ShineButton_shine_distance_multiple, shineParams.shineDistanceMultiple);
        shineParams.shineTurnAngle = a.getFloat(R.styleable.ShineButton_shine_turn_angle, shineParams.shineTurnAngle);
        shineParams.smallShineColor = a.getColor(R.styleable.ShineButton_small_shine_color, shineParams.smallShineColor);
        shineParams.smallShineOffsetAngle = a.getFloat(R.styleable.ShineButton_small_shine_offset_angle, shineParams.smallShineOffsetAngle);
        shineParams.shineSize = a.getDimensionPixelSize(R.styleable.ShineButton_shine_size, shineParams.shineSize);
        a.recycle();
        setSrcColor(btnColor);
    }

}