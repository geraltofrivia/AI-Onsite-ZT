// https://github.com/openboard-team/openboard/tree/c3772cd56e770975ea5570db903f93b199de8b32/app/src/main/java/org/dslul/openboard/inputmethod/latin/settings/SeekBarDialogPreference.java#L51-L60
public class TempClass {
    public SeekBarDialogPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.SeekBarDialogPreference, 0, 0);
        mMaxValue = a.getInt(R.styleable.SeekBarDialogPreference_maxValue, 0);
        mMinValue = a.getInt(R.styleable.SeekBarDialogPreference_minValue, 0);
        mStepValue = a.getInt(R.styleable.SeekBarDialogPreference_stepValue, 0);
        a.recycle();
        setDialogLayoutResource(R.layout.seek_bar_dialog);
    }

}