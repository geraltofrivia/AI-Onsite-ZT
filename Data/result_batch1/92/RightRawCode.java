// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/support/lib/LongClickableLinkMovementMethod.java#L195-L258
public class TempClass {
    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer,
            MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {

            Layout layout = widget.getLayout();

            if (layout == null) {
                return super.onTouchEvent(widget, buffer, event);
            }

            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            MyURLSpan[] link = buffer.getSpans(off, off, MyURLSpan.class);

            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    if (!mHasPerformedLongPress) {
                        link[0].onClick(widget);
                    }
                    pressed = false;
                    lastEvent = new float[2];
                } else if (action == MotionEvent.ACTION_DOWN) {
                    pressed = true;
                    lastEvent[0] = event.getX();
                    lastEvent[1] = event.getY();
                    checkForLongClick(link, widget);

                }

                return true;
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            float[] position = {event.getX(), event.getY()};
//            int slop = ViewConfiguration.get(widget.getContext()).getScaledTouchSlop();
            int slop = 6;
            float xInstance = Math.abs(lastEvent[0] - position[0]);
            float yInstance = Math.abs(lastEvent[1] - position[1]);
            double instance = Math.sqrt(Math.hypot(xInstance, yInstance));
            if (instance > slop) {
                pressed = false;
            }
        } else if (action == MotionEvent.ACTION_CANCEL) {
            pressed = false;
            lastEvent = new float[2];
        } else {
            pressed = false;
            lastEvent = new float[2];
        }
        return super.onTouchEvent(widget, buffer, event);
    }

}