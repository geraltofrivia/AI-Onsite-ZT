// https://github.com/ukanth/afwall/tree/7781903380d125a8fbfd9c7ec51b093350ebe532/app/src/main/java/dev/ukanth/ufirewall/activity/LogDetailActivity.java#L92-L104
public class TempClass {
    private void initTheme() {
        switch (G.getSelectedTheme()) {
            case "D":
                setTheme(R.style.AppDarkTheme);
                break;
            case "L":
                setTheme(R.style.AppLightTheme);
                break;
            case "B":
                setTheme(R.style.AppBlackTheme);
                break;
        }
    }

}