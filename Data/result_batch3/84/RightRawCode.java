// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/UIComponents/src/main/java/org/gephi/ui/components/JRangeSlider.java#L606-L623
public class TempClass {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!slider.isEnabled()) {
                    return;
                }

                currentMouseX = e.getX();
                currentMouseY = e.getY();

                if (lowerDragging) {
                    slider.setValueIsAdjusting(true);
                    moveLowerThumb();

                } else if (upperDragging) {
                    slider.setValueIsAdjusting(true);
                    moveUpperThumb();
                }
            }

}