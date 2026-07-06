// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCL/src/main/java/org/jackhuang/hmcl/ui/construct/TabHeader.java#L312-L356
public class TempClass {
                    private void runTimeline(double newTransX, double newWidth) {
                        double lineWidth = selectedTabLine.prefWidth(-1.0D);
                        if (isAnimating()) {
                            timeline.stop();
                            double tempScaleX = scale.getX();
                            if (rotate.getAngle() != 0.0D) {
                                rotate.setAngle(0.0D);
                                double tempWidth = tempScaleX * lineWidth;
                                selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() - tempWidth);
                            }
                        }

                        double oldScaleX = scale.getX();
                        double oldWidth = lineWidth * oldScaleX;
                        double oldTransX = selectedTabLine.getTranslateX();
                        double newScaleX = newWidth * oldScaleX / oldWidth;
                        selectedTabLineOffset = newTransX;
                        // newTransX += offsetStart * (double)this.direction;
                        double transDiff = newTransX - oldTransX;
                        if (transDiff < 0.0D) {
                            selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() + oldWidth);
                            newTransX += newWidth;
                            rotate.setAngle(180.0D);
                        }

                        timeline = new Timeline(
                                new KeyFrame(
                                        Duration.ZERO,
                                        new KeyValue(selectedTabLine.translateXProperty(), selectedTabLine.getTranslateX(), Interpolator.EASE_BOTH)
                                ),
                                new KeyFrame(
                                        Duration.seconds(0.24D),
                                        new KeyValue(scale.xProperty(), newScaleX, Interpolator.EASE_BOTH),
                                        new KeyValue(selectedTabLine.translateXProperty(), newTransX, Interpolator.EASE_BOTH)
                                )
                        );
                        timeline.setOnFinished((finish) -> {
                            if (rotate.getAngle() != 0.0D) {
                                rotate.setAngle(0.0D);
                                selectedTabLine.setTranslateX(selectedTabLine.getTranslateX() - newWidth);
                            }

                        });
                        timeline.play();
                    }

}