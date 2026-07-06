// https://github.com/federicoiosue/Omni-Notes/tree/a5b2ae231e35dae38a5c4274dc27672a88ddc0d5/omniNotes/src/main/java/it/feio/android/omninotes/MainActivity.java#L101-L115
public class TempClass {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.OmniNotesTheme_ApiSpec);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    EventBus.getDefault().register(this);
    Prefs.getPreferences().registerOnSharedPreferenceChangeListener(this);
    new NotificationsHelper(this).askToEnableNotifications(this);

    initUI();
  }

}