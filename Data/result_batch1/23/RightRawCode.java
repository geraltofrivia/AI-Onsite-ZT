// https://github.com/Haleydu/Cimoc/tree/891eb3f7ce51a940fd144795798c03b8e2058c0f/app/src/main/java/com/haleydu/cimoc/ui/widget/ChapterButton.java#L60-L75
public class TempClass {
    private void initDrawableState() {
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setStroke((int) ViewUtils.dpToPixel(1, getContext()), normalColor);
        normalDrawable.setCornerRadius(ViewUtils.dpToPixel(18, getContext()));
        normalDrawable.setColor(Color.TRANSPARENT);

        GradientDrawable selectedDrawable = new GradientDrawable();
        selectedDrawable.setStroke((int) ViewUtils.dpToPixel(1, getContext()), accentColor);
        selectedDrawable.setCornerRadius(ViewUtils.dpToPixel(18, getContext()));
        selectedDrawable.setColor(accentColor);

        StateListDrawable stateList = new StateListDrawable();
        stateList.addState(NORMAL_STATE, normalDrawable);
        stateList.addState(SELECTED_STATE, selectedDrawable);
        setBackgroundDrawable(stateList);
    }

}