// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/org/schabi/newpipe/views/FocusAwareDrawerLayout.java#L79-L104
public class TempClass {
    @Override
    public void addFocusables(final ArrayList<View> views, final int direction,
                              final int focusableMode) {
        boolean hasOpenPanels = false;
        View content = null;

        for (int i = 0; i < getChildCount(); ++i) {
            final View child = getChildAt(i);

            final DrawerLayout.LayoutParams lp =
                    (DrawerLayout.LayoutParams) child.getLayoutParams();

            if (lp.gravity == 0) {
                content = child;
            } else {
                if (isDrawerVisible(child)) {
                    hasOpenPanels = true;
                    child.addFocusables(views, direction, focusableMode);
                }
            }
        }

        if (content != null && !hasOpenPanels) {
            content.addFocusables(views, direction, focusableMode);
        }
    }

}