// https://github.com/FongMi/TV/tree/a03ac275211c5afa5764e9cd7724de5405f4d029/app/src/mobile/java/com/fongmi/android/tv/ui/base/BaseActivity.java#L33-L42
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (transparent()) setTransparent(this);
        setContentView(getBinding().getRoot());
        EventBus.getDefault().register(this);
        initView(savedInstanceState);
        setBackCallback();
        initEvent();
    }

}