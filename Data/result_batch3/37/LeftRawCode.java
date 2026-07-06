// https://github.com/alibaba/Tangram-Android/tree/e8fe44d8aa0fd124afaefe945e65da3df9c9244c/tangram/src/main/java/com/tmall/wireless/tangram/ext/SwipeItemTouchListener.java#L283-L301
public class TempClass {
    private View findCanScrollView(View v) {
        if (v instanceof ViewGroup) {
            ViewGroup target = (ViewGroup) v;
            if ((target instanceof UltraViewPager || target instanceof RecyclerView)
                && target.getVisibility() == View.VISIBLE) {
                return target;
            } else {
                for (int i = 0; i < target.getChildCount(); ++i) {
                    View view = findCanScrollView(target.getChildAt(i));
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

}