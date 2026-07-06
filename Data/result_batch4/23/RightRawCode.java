// https://github.com/FongMi/TV/tree/a03ac275211c5afa5764e9cd7724de5405f4d029/app/src/leanback/java/com/fongmi/android/tv/ui/custom/CustomViewPager.java#L54-L110
public class TempClass {
    @Override
    public boolean arrowScroll(int direction) {
        boolean handled = false;
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            for (ViewParent parent = currentFocused.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                if (parent == this) {
                    isChild = true;
                    break;
                }
            }
            if (!isChild) {
                currentFocused = null;
            }
        }
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        if (nextFocused != null && nextFocused != currentFocused) {
            if (direction == View.FOCUS_LEFT) {
                int nextLeft = getChildRectInPagerCoordinates(rect, nextFocused).left;
                int currLeft = getChildRectInPagerCoordinates(rect, currentFocused).left;
                if (currentFocused != null && nextLeft >= currLeft) {
                    handled = pageLeft();
                } else {
                    handled = nextFocused.requestFocus();
                }
            } else if (direction == View.FOCUS_RIGHT) {
                int nextLeft = getChildRectInPagerCoordinates(rect, nextFocused).left;
                int currLeft = getChildRectInPagerCoordinates(rect, currentFocused).left;
                if (currentFocused != null && nextLeft <= currLeft) {
                    handled = pageRight();
                } else {
                    handled = nextFocused.requestFocus();
                }
            }
        } else if (direction == FOCUS_LEFT) {
            if (getCurrentItem() == 0) {
                shake(currentFocused);
                handled = true;
            } else {
                handled = pageLeft();
            }
        } else if (direction == FOCUS_RIGHT) {
            if (getAdapter() != null && getCurrentItem() == getAdapter().getCount() - 1) {
                shake(currentFocused);
                handled = true;
            } else {
                handled = pageRight();
            }
        }
        if (handled) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return handled;
    }

}