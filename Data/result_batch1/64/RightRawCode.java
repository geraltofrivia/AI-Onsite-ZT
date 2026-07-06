// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCL/src/main/java/org/jackhuang/hmcl/ui/decorator/DecoratorSkin.java#L344-L387
public class TempClass {
    private void onMouseMoved(MouseEvent mouseEvent) {
        if (!primaryStage.isFullScreen() && primaryStage.isResizable()) {
            double x = mouseEvent.getX(), y = mouseEvent.getY();
            Bounds boundsInParent = root.getBoundsInParent();
            double diagonalSize = root.snappedLeftInset() + 10;
            if (this.isRightEdge(x, y, boundsInParent)) {
                if (y < diagonalSize) {
                    root.setCursor(Cursor.NE_RESIZE);
                } else if (y > root.getHeight() - diagonalSize) {
                    root.setCursor(Cursor.SE_RESIZE);
                } else {
                    root.setCursor(Cursor.E_RESIZE);
                }
            } else if (this.isLeftEdge(x, y, boundsInParent)) {
                if (y < diagonalSize) {
                    root.setCursor(Cursor.NW_RESIZE);
                } else if (y > root.getHeight() - diagonalSize) {
                    root.setCursor(Cursor.SW_RESIZE);
                } else {
                    root.setCursor(Cursor.W_RESIZE);
                }
            } else if (this.isTopEdge(x, y, boundsInParent)) {
                if (x < diagonalSize) {
                    root.setCursor(Cursor.NW_RESIZE);
                } else if (x > root.getWidth() - diagonalSize) {
                    root.setCursor(Cursor.NE_RESIZE);
                } else {
                    root.setCursor(Cursor.N_RESIZE);
                }
            } else if (this.isBottomEdge(x, y, boundsInParent)) {
                if (x < diagonalSize) {
                    root.setCursor(Cursor.SW_RESIZE);
                } else if (x > root.getWidth() - diagonalSize) {
                    root.setCursor(Cursor.SE_RESIZE);
                } else {
                    root.setCursor(Cursor.S_RESIZE);
                }
            } else {
                root.setCursor(Cursor.DEFAULT);
            }
        } else {
            root.setCursor(Cursor.DEFAULT);
        }
    }

}