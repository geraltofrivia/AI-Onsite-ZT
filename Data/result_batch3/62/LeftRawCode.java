// https://github.com/zfdang/Android-Touch-Helper/tree/9a131ceda364e39ad283b848fdbdff7c03d54d9f/app/src/main/java/com/zfdang/touchhelper/Utilities.java#L39-L69
public class TempClass {
    public static String describeAccessibilityNode(AccessibilityNodeInfo e){
        if(e == null) {
            return "null";
        }

        String result = "Node";

        result += " class =" + e.getClassName().toString();

        final Rect rect = new Rect();
        e.getBoundsInScreen(rect);
        result += String.format(" Position=[%d, %d, %d, %d]", rect.left, rect.right, rect.top, rect.bottom);


        CharSequence id = e.getViewIdResourceName();
        if(id != null) {
            result += " ResourceId=" + id.toString();
        }

        CharSequence description = e.getContentDescription();
        if(description != null) {
            result += " Description=" + description.toString();
        }

        CharSequence text = e.getText();
        if(text != null) {
            result += " Text=" + text.toString();
        }

        return result;
    }

}