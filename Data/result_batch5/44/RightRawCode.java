// https://github.com/fossasia/phimpme-android/tree/60acbd2e5691d3d2f794b86e1277bfedab481035/app/src/main/java/org/fossasia/phimpme/opencamera/Camera/CameraActivity.java#L174-L515
public class TempClass {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    long debug_time = 0;
    if (MyDebug.LOG) {
      Log.d(TAG, "onCreate");
      debug_time = System.currentTimeMillis();
    }
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    ButterKnife.bind(this);
    PreferenceManager.setDefaultValues(
        this, R.xml.preferences, false); // initialise any unset preferences to their default values
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after setting default preference values: "
              + (System.currentTimeMillis() - debug_time));

    if (getIntent() != null && getIntent().getExtras() != null) {
      // whether called from testing
      is_test = getIntent().getExtras().getBoolean("test_project");
      if (MyDebug.LOG) Log.d(TAG, "is_test: " + is_test);
    }
    if (getIntent() != null && getIntent().getExtras() != null) {
      // whether called from Take Photo widget
      isFromOutside = true;
      hideNavigationBar();
      if (MyDebug.LOG)
        Log.d(TAG, "take_photo?: " + getIntent().getExtras().getBoolean(TakePhoto.TAKE_PHOTO));
    }
    SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
    bundle = new TinyDB(getBaseContext());

    // determine whether we should support "auto stabilise" feature
    // risk of running out of memory on lower end devices, due to manipulation of large bitmaps
    ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    if (MyDebug.LOG) {
      Log.d(TAG, "standard max memory = " + activityManager.getMemoryClass() + "MB");
      Log.d(TAG, "large max memory = " + activityManager.getLargeMemoryClass() + "MB");
    }
    // if( activityManager.getMemoryClass() >= 128 ) { // test
    if (activityManager.getLargeMemoryClass() >= 128) {
      supports_auto_stabilise = true;
    }
    if (MyDebug.LOG) Log.d(TAG, "supports_auto_stabilise? " + supports_auto_stabilise);

    // hack to rule out phones unlikely to have 4K video, so no point even offering the option!
    // both S5 and Note 3 have 128MB standard and 512MB large heap (tested via Samsung RTL), as does
    // Galaxy K Zoom
    // also added the check for having 128MB standard heap, to support modded LG G2, which has 128MB
    // standard, 256MB large - see https://sourceforge.net/p/opencamera/tickets/9/
    if (activityManager.getMemoryClass() >= 128 || activityManager.getLargeMemoryClass() >= 512) {
      supports_force_video_4k = true;
    }
    if (MyDebug.LOG) Log.d(TAG, "supports_force_video_4k? " + supports_force_video_4k);

    // set up components
    mainUI = new MainUI(this);
    applicationInterface = new MyApplicationInterface(this, savedInstanceState);
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after creating application interface: "
              + (System.currentTimeMillis() - debug_time));
    textFormatter = new TextFormatter(this);

    // determine whether we support Camera2 API
    initCamera2Support();

    // set up window flags for normal operation
    setWindowFlagsForCamera();
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after setting window flags: "
              + (System.currentTimeMillis() - debug_time));

    save_location_history =
        new SaveLocationHistory(this, "save_location_history", getStorageUtils().getSaveLocation());
    if (applicationInterface.getStorageUtils().isUsingSAF()) {
      if (MyDebug.LOG) Log.d(TAG, "create new SaveLocationHistory for SAF");
      save_location_history_saf =
          new SaveLocationHistory(
              this, "save_location_history_saf", getStorageUtils().getSaveLocationSAF());
    }
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after updating folder history: "
              + (System.currentTimeMillis() - debug_time));

    // set up sensors
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    // accelerometer sensor (for device orientation)
    if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
      if (MyDebug.LOG) Log.d(TAG, "found accelerometer");
      mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    } else {
      if (MyDebug.LOG) Log.d(TAG, "no support for accelerometer");
    }
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after creating accelerometer sensor: "
              + (System.currentTimeMillis() - debug_time));

    // magnetic sensor (for compass direction)
    if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
      if (MyDebug.LOG) Log.d(TAG, "found magnetic sensor");
      mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    } else {
      if (MyDebug.LOG) Log.d(TAG, "no support for magnetic sensor");
    }
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after creating magnetic sensor: "
              + (System.currentTimeMillis() - debug_time));

    // clear any seek bars (just in case??)
    mainUI.clearSeekBar();

    // set up the camera and its preview
    preview = new Preview(applicationInterface, ((ViewGroup) this.findViewById(R.id.preview)));
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after creating preview: " + (System.currentTimeMillis() - debug_time));

    // initialise on-screen button visibility
    View switchCameraButton = findViewById(R.id.switch_camera);
    switchCameraButton.setVisibility(
        preview.getCameraControllerManager().getNumberOfCameras() > 1 ? View.VISIBLE : View.GONE);
    View speechRecognizerButton = findViewById(R.id.audio_control);
    speechRecognizerButton.setVisibility(
        View.GONE); // disabled by default, until the speech recognizer is created
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after setting button visibility: "
              + (System.currentTimeMillis() - debug_time));

    // listen for orientation event change
    orientationEventListener =
        new OrientationEventListener(this) {
          @Override
          public void onOrientationChanged(int orientation) {
            CameraActivity.this.mainUI.onOrientationChanged(orientation);
          }
        };
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after setting orientation event listener: "
              + (System.currentTimeMillis() - debug_time));

    // listen for gestures
    gestureDetector = new GestureDetector(this, new MyGestureDetector());
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after creating gesture detector: "
              + (System.currentTimeMillis() - debug_time));

    // set up listener to handle immersive mode options
    View decorView = getWindow().getDecorView();
    decorView.setOnSystemUiVisibilityChangeListener(
        new View.OnSystemUiVisibilityChangeListener() {
          @Override
          public void onSystemUiVisibilityChange(int visibility) {
            // Note that system bars will only be "visible" if none of the
            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
            if (!usingKitKatImmersiveMode()) return;
            if (MyDebug.LOG) Log.d(TAG, "onSystemUiVisibilityChange: " + visibility);
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
              if (MyDebug.LOG) Log.d(TAG, "system bars now visible");
              // The system bars are visible. Make any desired
              // adjustments to your UI, such as showing the action bar or
              // other navigational controls.
              mainUI.setImmersiveMode(false);
              setImmersiveTimer();
            } else {
              if (MyDebug.LOG) Log.d(TAG, "system bars now NOT visible");
              // The system bars are NOT visible. Make any desired
              // adjustments to your UI, such as hiding the action bar or
              // other navigational controls.
              mainUI.setImmersiveMode(true);
            }
          }
        });
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after setting immersive mode listener: "
              + (System.currentTimeMillis() - debug_time));

    // show "about" dialog for first time use; also set some per-device defaults
    boolean has_done_first_time =
        sharedPreferences.contains(PreferenceKeys.getFirstTimePreferenceKey());
    if (MyDebug.LOG) Log.d(TAG, "has_done_first_time: " + has_done_first_time);
    if (!has_done_first_time) {
      setDeviceDefaults(this);
    }
    if (!has_done_first_time) {
      setFirstTimeFlag();
    }

    setModeFromIntents(savedInstanceState);

    // load icon+s
    preloadIcons(R.array.flash_icons);
    preloadIcons(R.array.focus_mode_icons);
    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: time after preloading icons: " + (System.currentTimeMillis() - debug_time));

    // initialise text to speech engine
    textToSpeechSuccess = false;
    // run in separate thread so as to not delay startup time
    new Thread(
            new Runnable() {
              public void run() {
                textToSpeech =
                    new TextToSpeech(
                        CameraActivity.this,
                        new TextToSpeech.OnInitListener() {
                          @Override
                          public void onInit(int status) {
                            if (MyDebug.LOG) Log.d(TAG, "TextToSpeech initialised");
                            if (status == TextToSpeech.SUCCESS) {
                              textToSpeechSuccess = true;
                              if (MyDebug.LOG) Log.d(TAG, "TextToSpeech succeeded");
                            } else {
                              if (MyDebug.LOG) Log.d(TAG, "TextToSpeech failed");
                            }
                          }
                        });
              }
            })
        .start();

    if (MyDebug.LOG)
      Log.d(
          TAG,
          "onCreate: total time for Activity startup: "
              + (System.currentTimeMillis() - debug_time));

    if (!isFromOutside) {
      BasicCallBack basicCallBack =
          new BasicCallBack() {
            @Override
            public void callBack(int status, Object path) {
              if (status == Constants.SUCCESS) {
                final String filepath = path.toString();
                final SharedPreferences sharedPreferences =
                    getDefaultSharedPreferences(CameraActivity.this);
                String mode =
                    sharedPreferences.getString(PreferenceKeys.getPhotoModePreferenceKey(), "");
                final String burst_mode =
                    sharedPreferences.getString(PreferenceKeys.getBurstModePreferenceKey(), "");

                if (clicks_count == 0) { // To start progress dialog once
                  Handler h = new Handler(Looper.getMainLooper());
                  h.post(
                      new Runnable() {
                        public void run() {
                          progressDialog = new ProgressDialog(CameraActivity.this);
                          progressDialog.setMessage("Generating image. Please wait...");
                          progressDialog.show();
                        }
                      });
                }

                clicks_count++; // Count till max defined image is captured and saved
                if (!("preference_photo_mode_expo_bracketing").equals(mode)
                    && Integer.parseInt(burst_mode) == 1) {
                  Boolean preference_pause_preview =
                      sharedPreferences.getBoolean("preference_pause_preview", true);
                  if (!preference_pause_preview) {
                    CameraActivity.this.runOnUiThread(
                        new Runnable() {
                          @Override
                          public void run() {
                            progressDialog.hide();
                          }
                        });
                    Thread t =
                        new Thread(
                            new Runnable() {
                              @Override
                              public void run() {
                                File f = new File(filepath);
                                FileOutputStream fileOutputStream;
                                try {
                                  fileOutputStream = openFileOutput(f.getName(), MODE_PRIVATE);
                                  fileOutputStream.write(f.getName().getBytes());
                                  fileOutputStream.close();
                                } catch (FileNotFoundException e) {
                                  e.printStackTrace();
                                } catch (IOException e) {
                                  e.printStackTrace();
                                }
                              }
                            });
                    t.start();
                  } else {
                    clicks_count = 0;
                    Intent intent = new Intent(CameraActivity.this, PhotoActivity.class);
                    intent.putExtra("filepath", filepath);
                    startActivity(intent);
                  }
                } else if (("preference_photo_mode_expo_bracketing").equals(mode)
                    && clicks_count
                        >= bundle.getInt(
                            "max_expo_bracketing_n_images")) { // Start Activity once when the third
                  // image is saved
                  clicks_count =
                      0; // Turn image count to zero in case user wants to click another set of
                  // photos.
                  Intent intent = new Intent(REVIEW_ACTION, Uri.fromFile(new File(filepath)));
                  intent.setClass(getApplicationContext(), SingleMediaActivity.class);
                  startActivity(intent);
                } else if (Integer.parseInt(burst_mode) > 1) {
                  clicks_count =
                      0; // Turn image count to zero in case user wants to click another set of
                  // photos.
                  Intent intent = new Intent(REVIEW_ACTION, Uri.fromFile(new File(filepath)));
                  intent.setClass(getApplicationContext(), SingleMediaActivity.class);
                  startActivity(intent);
                }
              }
            }
          };
      ImageSaver.setBasicCallBack(basicCallBack);
    }
    toggle = findViewById(R.id.toggle_button);
    increaseZoom.setOnClickListener(this);
    decreaseZoom.setOnClickListener(this);
  }

}