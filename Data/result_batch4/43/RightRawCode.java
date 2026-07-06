// https://github.com/TeamAmaze/AmazeFileManager/tree/622d0f8b5fc75acb828e68dedb98c776ac9ca53c/app/src/main/java/com/amaze/filemanager/ui/views/CustomLinearSmoothScroller.java#L129-L138
public class TempClass {
  @Override
  protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
    final int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
    final int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference());
    final int distance = (int) Math.sqrt(dx * dx + dy * dy);
    final int time = calculateTimeForDeceleration(distance);
    if (time > 0) {
      action.update(-dx, -dy, time, mDecelerateInterpolator);
    }
  }

}