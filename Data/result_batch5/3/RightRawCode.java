// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/support/lib/SwipeFrameLayout.java#L65-L114
public class TempClass {
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (scroller.computeScrollOffset()) {
            scroller.abortAnimation();
        }

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                initPointLocation[0] = ev.getRawX();
                initPointLocation[1] = ev.getRawY();

                return false;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    gestureDetector.onTouchEvent(ev);
                    return true;
                }

                float xx = ev.getRawX();
                if ((xx > initPointLocation[0] + Utility.dip2px(OFFSET))
                        && initPointLocation[0] <= max_motion_event_down_x_position) {

                    isDragging = true;
                    gestureDetector.onTouchEvent(ev);

                    return true;
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;

                if (initPointLocation[0] <= max_motion_event_down_x_position) {
                    int x = (int) (ev.getRawX() - initPointLocation[0]);
                    initPointLocation[0] = 0f;
                    initPointLocation[1] = 0f;

                    if (x > (Utility.getScreenWidth() / 2)) {
                        closeActivity();
                        return true;
                    } else {
                        restoreActivity();
                    }
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

}