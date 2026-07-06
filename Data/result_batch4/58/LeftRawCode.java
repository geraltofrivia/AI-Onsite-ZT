// https://github.com/Bearded-Hen/Android-Bootstrap/tree/b3d62cc1847e26d420c53c92665a4fe1e6ee7ecf/AndroidBootstrap/src/main/java/com/beardedhen/androidbootstrap/BootstrapDropDown.java#L144-L167
public class TempClass {
    private void createDropDown() {
        ScrollView dropdownView = createDropDownView();
        dropdownWindow = new PopupWindow();
        dropdownWindow.setFocusable(true);
        dropdownWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        if (!isInEditMode()) {
            dropdownWindow.setBackgroundDrawable(DrawableUtils.resolveDrawable(android.R.drawable
                                                                                       .dialog_holo_light_frame, getContext()));
        }

        dropdownWindow.setContentView(dropdownView);
        dropdownWindow.setOnDismissListener(this);
        dropdownWindow.setAnimationStyle(android.R.style.Animation_Activity);
        float longestStringWidth = measureStringWidth(getLongestString(dropdownData))
                + DimenUtils.dpToPixels((baselineItemRightPadding + baselineItemLeftPadding) * bootstrapSize);

        if (longestStringWidth < getMeasuredWidth()) {
            dropdownWindow.setWidth(DimenUtils.dpToPixels(getMeasuredWidth()));
        }
        else {
            dropdownWindow.setWidth((int) longestStringWidth + DimenUtils.dpToPixels(8));
        }
    }

}