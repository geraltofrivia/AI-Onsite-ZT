// https://github.com/FongMi/TV/tree/a03ac275211c5afa5764e9cd7724de5405f4d029/catvod/src/main/java/com/github/catvod/utils/Util.java#L89-L103
public class TempClass {
    public static String md5(File file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[4096];
            int count;
            while ((count = fis.read(bytes)) != -1) digest.update(bytes, 0, count);
            fis.close();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest.digest()) sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

}