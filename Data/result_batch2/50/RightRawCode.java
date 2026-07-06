// https://github.com/fossasia/phimpme-android/tree/60acbd2e5691d3d2f794b86e1277bfedab481035/app/src/main/java/org/fossasia/phimpme/opencamera/UI/DrawPreview.java#L247-L258
public class TempClass {
  private String getTimeStringFromSeconds(long time) {
    int secs = (int) (time % 60);
    time /= 60;
    int mins = (int) (time % 60);
    time /= 60;
    long hours = time;
    return hours
        + ":"
        + String.format(Locale.getDefault(), "%02d", mins)
        + ":"
        + String.format(Locale.getDefault(), "%02d", secs);
  }

}