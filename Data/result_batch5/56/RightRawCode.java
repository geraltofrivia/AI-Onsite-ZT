// https://github.com/MeteorDevelopment/meteor-client/tree/4da2b5cb1e515d592cf6e723480cf640ddfb5df0/src/main/java/meteordevelopment/meteorclient/gui/WidgetScreen.java#L138-L150
public class TempClass {
    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        if (locked) return;

        double s = mc.getWindow().getScaleFactor();
        mouseX *= s;
        mouseY *= s;

        root.mouseMoved(mouseX, mouseY, lastMouseX, lastMouseY);

        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

}