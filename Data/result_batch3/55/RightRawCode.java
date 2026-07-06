// https://github.com/airsonic/airsonic/tree/5ccca059d5cfe3dd19734c27861b434ea21b43d8/airsonic-main/src/main/java/org/airsonic/player/taglib/UrlTag.java#L142-L153
public class TempClass {
    static private boolean isAsciiAlphaNumeric(String s) {
        if (s == null) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!CharUtils.isAsciiAlphanumeric(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}