// https://github.com/UnevenSoftware/LeafPic/tree/cfec78746776ed4bbccf9387bfc3f6ba1bb2c181/app/src/main/java/org/horaapps/leafpic/views/videoplayer/CustomPlayBackController.java#L315-L326
public class TempClass {
    private String stringForTime(long timeMs) {
        if (timeMs == C.TIME_UNSET) {
            timeMs = 0;
        }
        long totalSeconds = (timeMs + 500) / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        formatBuilder.setLength(0);
        return hours > 0 ? formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
                : formatter.format("%02d:%02d", minutes, seconds).toString();
    }

}