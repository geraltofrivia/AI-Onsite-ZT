// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/VisualizationImpl/src/main/java/org/gephi/visualization/VizController.java#L190-L201
public class TempClass {
    public void destroy() {
        engine.stopDisplay();
        drawable.destroy();
        engine = null;
        scheduler = null;
        graphIO = null;
        vizEventManager = null;
        dataBridge = null;
        textManager = null;
        screenshotMaker = null;
        selectionManager = null;
    }

}