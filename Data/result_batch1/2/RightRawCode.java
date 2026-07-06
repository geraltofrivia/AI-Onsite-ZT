// https://github.com/FongMi/TV/tree/a03ac275211c5afa5764e9cd7724de5405f4d029/catvod/src/main/java/com/github/catvod/utils/Path.java#L133-L143
public class TempClass {
    public static String read(InputStream is) {
        try {
            byte[] data = new byte[is.available()];
            is.read(data);
            is.close();
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}