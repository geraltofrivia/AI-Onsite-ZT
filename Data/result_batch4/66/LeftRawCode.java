// https://github.com/aporter/coursera-android/tree/157373885fbfa18b83fa97cd46f6a003905970ea/Examples2018/LocationGetLocation/app/src/main/java/course/examples/location/getlocation/LocationGetLocationActivity.java#L52-L74
public class TempClass {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mAccuracyView = findViewById(R.id.accuracy_view);
        mTimeView = findViewById(R.id.time_view);
        mLatView = findViewById(R.id.lat_view);
        mLngView = findViewById(R.id.lng_view);

        // Acquire reference to the LocationManager
        if (null == (mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE)))
            finish();

        mLocationListener = getLocationListener();

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOC_PERM_ONCREATE);
        } else {
            getAndDisplayBestLastKnownLocation();
        }
    }

}