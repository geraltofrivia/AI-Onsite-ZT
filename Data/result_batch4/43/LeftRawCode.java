// https://github.com/CeuiLiSA/Pixiv-Shaft/tree/95ce81191beee56f4f66fa964a7c6c8dd9d23646/app/src/main/java/ceui/lisa/helper/StaggeredManager.java#L31-L59
public class TempClass {
            @Override
            protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
                try {
                    /*
                     * Android 判断一个 View 是否可见 getLocalVisibleRect(rect) 与 getGlobalVisibleRect(rect)
                     *
                     * https://www.bbsmax.com/A/ELPdow2d3a/
                     */

                    if (!targetView.getGlobalVisibleRect(new Rect())) {
                        Rect rect = new Rect();
                        recyclerView.getGlobalVisibleRect(rect);

                        int parentHeight = rect.bottom - rect.top;
                        int childHeight = targetView.getHeight();
                        int offset = (parentHeight - childHeight) / 2;

                        final int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
                        final int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference()) + offset;
                        final int distance = (int) Math.sqrt(dx * dx + dy * dy);
                        final int time = calculateTimeForDeceleration(distance);
                        if (time > 0) {
                            action.update(-dx, -dy, time, mDecelerateInterpolator);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

}