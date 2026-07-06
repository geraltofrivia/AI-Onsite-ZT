// https://github.com/PeterCxy/Shelter/tree/831c3753f205c4c8c1cd5bbb1f24e56b9d52eb76/app/src/main/java/net/typeblog/shelter/ui/MainActivity.java#L76-L93
public class TempClass {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.main_toolbar));
        mStorage = LocalStorageManager.getInstance();

        if (getSystemService(DevicePolicyManager.class).isProfileOwnerApp(getPackageName())) {
            // We are now in our own profile
            // We should never start the main activity here.
            android.util.Log.d("MainActivity", "started in user profile. stopping.");
            finish();
        } else {
            init();
        }

    }

}