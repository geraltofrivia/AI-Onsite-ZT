// https://github.com/lugg/react-native-config/tree/60b67c83aad62b6029835a97be46970a8ae2dd2f/android/src/main/java/com/lugg/RNCConfig/RNCConfigModule.java#L25-L50
public class TempClass {
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        try {
            Context context = getReactApplicationContext();
            int resId = context.getResources().getIdentifier("build_config_package", "string", context.getPackageName());
            String className;
            try {
                className = context.getString(resId);
            } catch (Resources.NotFoundException e) {
                className = getReactApplicationContext().getApplicationContext().getPackageName();
            }
            Class clazz = Class.forName(className + ".BuildConfig");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                try {
                    constants.put(f.getName(), f.get(null));
                } catch (IllegalAccessException e) {
                    Log.d("ReactNative", "ReactConfig: Could not access BuildConfig field " + f.getName());
                }
            }
        } catch (ClassNotFoundException e) {
            Log.d("ReactNative", "ReactConfig: Could not find BuildConfig class");
        }
        return constants;
    }

}