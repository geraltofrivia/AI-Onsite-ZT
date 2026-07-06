// https://github.com/ximsfei/Android-skin-support/tree/3eaa81f5999342018186fb28a2d915fc8605e4c4/third-part-support/flycotablayout/src/main/java/skin/support/flycotablayout/app/SkinFlycoTabLayoutInflater.java#L19-L37
public class TempClass {
    @Override
    public View createView(@NonNull Context context, String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "com.flyco.tablayout.SlidingTabLayout":
                view = new SkinSlidingTabLayout(context, attrs);
                break;
            case "com.flyco.tablayout.CommonTabLayout":
                view = new SkinCommonTabLayout(context, attrs);
                break;
            case "com.flyco.tablayout.SegmentTabLayout":
                view = new SkinSegmentTabLayout(context, attrs);
                break;
            case "com.flyco.tablayout.widget.MsgView":
                view = new SkinMsgView(context, attrs);
                break;
        }
        return view;
    }

}