// https://github.com/sshahine/JFoenix/tree/a1be87d91d578e7799bc308b3b1bbe1d742a020d/jfoenix/src/main/java/com/jfoenix/controls/JFXSnackbar.java#L263-L299
public class TempClass {
    public void close() {
        if (openAnimation != null) {
            openAnimation.stop();
        }
        if (this.isVisible()) {
            if (pauseTransition != null) {
                pauseTransition.stop();
                pauseTransition = null;
            }
            Timeline closeAnimation = new Timeline(
                new KeyFrame(
                    Duration.ZERO,
                    e -> this.toFront(),
                    new KeyValue(this.opacityProperty(), 1, easeInterpolator),
                    new KeyValue(this.translateYProperty(), 0, easeInterpolator)
                ),
                new KeyFrame(
                    Duration.millis(290),
                    new KeyValue(this.visibleProperty(), true, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.millis(300),
                    e -> this.toBack(),
                    new KeyValue(this.visibleProperty(), false, Interpolator.EASE_BOTH),
                    new KeyValue(this.translateYProperty(),
                        this.getLayoutBounds().getHeight(),
                        easeInterpolator),
                    new KeyValue(this.opacityProperty(), 0, easeInterpolator)
                )
            );
            closeAnimation.setCycleCount(1);
            closeAnimation.setOnFinished(e -> {
                resetPseudoClass();
                processSnackbar();
            });
            closeAnimation.play();
        }
    }

}