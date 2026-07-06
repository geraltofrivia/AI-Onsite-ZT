// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/graphics/LauncherPreviewRenderer.java#L304-L328
public class TempClass {
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if ("TextClock".equals(name)) {
            // Workaround for TextClock accessing handler for unregistering ticker.
            return new TextClock(context, attrs) {

                @Override
                public Handler getHandler() {
                    return mUiHandler;
                }
            };
        } else if (!"fragment".equals(name)) {
            return null;
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PreviewFragment);
        FragmentWithPreview f = (FragmentWithPreview) Fragment.instantiate(
                context, ta.getString(R.styleable.PreviewFragment_android_name));
        f.enterPreviewMode(context);
        f.onInit(null);

        View view = f.onCreateView(LayoutInflater.from(context), (ViewGroup) parent, null);
        view.setId(ta.getInt(R.styleable.PreviewFragment_android_id, View.NO_ID));
        return view;
    }

}