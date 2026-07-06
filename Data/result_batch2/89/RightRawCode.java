// https://github.com/airsonic/airsonic/tree/5ccca059d5cfe3dd19734c27861b434ea21b43d8/airsonic-main/src/main/java/org/airsonic/player/controller/InternalHelpController.java#L408-L423
public class TempClass {
    private static List<String> getLatestLogEntries(File logFile) {
        List<String> lines = new ArrayList<>(LOG_LINES_TO_SHOW);
        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(logFile, Charset.defaultCharset())) {
            String current;
            while ((current = reader.readLine()) != null) {
                if (lines.size() >= LOG_LINES_TO_SHOW) {
                    break;
                }
                lines.add(0, current);
            }
            return lines;
        } catch (IOException e) {
            LOG.warn("Could not open log file " + logFile, e);
            return null;
        }
    }

}