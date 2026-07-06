// https://github.com/MewX/light-novel-library_Wenku8_Android/tree/68ea337ae0e34cec1a7a27bf351e4d0465b394aa/eclipse-android-old/LightNovelLibrary/src/com/ecloud/pulltozoomview/PullToZoomListView.java#L80-L113
public class TempClass {
    private void init(AttributeSet attrs) {
        mHeaderContainer = new FrameLayout(getContext());
        if (attrs != null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            //初始化状态View
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PullToZoomListView);

            int headViewResId = a.getResourceId(R.styleable.PullToZoomListView_listHeadView, 0);
            if (headViewResId > 0) {
                mHeaderView = mLayoutInflater.inflate(headViewResId, null, false);
                mHeaderContainer.addView(mHeaderView);
                isHideHeader = false;
            } else {
                isHideHeader = true;
            }

            isParallax = a.getBoolean(R.styleable.PullToZoomListView_isHeadParallax, true);

            a.recycle();
        }

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;
        if (mHeaderView != null) {
            setHeaderViewSize(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
            addHeaderView(mHeaderContainer);
        }
        mScalingRunnable = new ScalingRunnable();
        super.setOnScrollListener(this);
    }

    public void setParallax(boolean isParallax) {

}