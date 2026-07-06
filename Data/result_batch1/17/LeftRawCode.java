// https://github.com/sshahine/JFoenix/tree/a1be87d91d578e7799bc308b3b1bbe1d742a020d/jfoenix/src/main/java/com/jfoenix/skins/JFXTabPaneSkin.java#L645-L707
public class TempClass {
        private void runTimeline(double newTransX, double newWidth) {
            if(selectedTabLine.getTranslateX() == newTransX
                && scale.getX() == newWidth) return;

            double tempScaleX = 0;
            double tempWidth = 0;
            final double lineWidth = selectedTabLine.prefWidth(-1);

            if ((isAnimating())) {
                timeline.stop();
                tempScaleX = scale.getX();
                if (rotate.getAngle() != 0) {
                    rotate.setAngle(0);
                    tempWidth = tempScaleX * lineWidth;
                    selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() - tempWidth);
                }
            }

            final double oldScaleX = scale.getX();
            final double oldWidth = lineWidth * oldScaleX;
            final double oldTransX = selectedTabLine.getTranslateX();
            final double newScaleX = (newWidth * oldScaleX) / oldWidth;
            // keep track of the original offset
            selectedTabLineOffset = newTransX;
            // add offset to the computed translation
            newTransX = newTransX + offsetStart * direction;
            final double transDiff = newTransX - oldTransX;


            double midScaleX = tempScaleX != 0 ?
                tempScaleX : ((Math.abs(transDiff)/translateScaleFactor + oldWidth) * oldScaleX) / oldWidth;

            if(midScaleX > Math.abs(transDiff) + newWidth){
                midScaleX = Math.abs(transDiff) + newWidth;
            }
            if (transDiff < 0) {
                selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() + oldWidth);
                newTransX += newWidth;
                rotate.setAngle(180);
            }

            timeline = new Timeline(
                new KeyFrame(
                    Duration.ZERO,
                    new KeyValue(selectedTabLine.translateXProperty(), selectedTabLine.getTranslateX(), Interpolator.EASE_BOTH)),
                new KeyFrame(
                    Duration.seconds(.12),
                    new KeyValue(scale.xProperty(), midScaleX, Interpolator.EASE_BOTH),
                    new KeyValue(selectedTabLine.translateXProperty(), selectedTabLine.getTranslateX(), Interpolator.EASE_BOTH)),
                new KeyFrame(
                    Duration.seconds(.24),
                    new KeyValue(scale.xProperty(), newScaleX, Interpolator.EASE_BOTH),
                    new KeyValue(selectedTabLine.translateXProperty(), newTransX, Interpolator.EASE_BOTH))
            );

            timeline.setOnFinished(finish -> {
                if (rotate.getAngle() != 0) {
                    rotate.setAngle(0);
                    selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() - newWidth);
                }
            });
            timeline.play();
        }

}