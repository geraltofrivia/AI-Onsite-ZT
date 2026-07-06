// https://github.com/geeeeeeeeek/WeChatLuckyMoney/tree/54a5201dfba6819f476a61e0e8096f13e3037ed9/app/src/main/java/xyz/monkeytong/hongbao/activities/MainActivity.java#L35-L52
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CrashReport.initCrashReport(getApplicationContext(), "900019352", false);
        Bugly.init(getApplicationContext(), "900019352", false);
        setContentView(R.layout.activity_main);
        pluginStatusText = (TextView) findViewById(R.id.layout_control_accessibility_text);
        pluginStatusIcon = (ImageView) findViewById(R.id.layout_control_accessibility_icon);

        handleMaterialStatusBar();

        explicitlyLoadPreferences();

        //监听AccessibilityService 变化
        accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        accessibilityManager.addAccessibilityStateChangeListener(this);
        updateServiceStatus();
    }

}