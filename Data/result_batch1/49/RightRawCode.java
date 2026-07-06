// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/fmllauncher/java/foxlaunch/Utils.java#L39-L57
public class TempClass {
    public static String getFileSHA256(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            final byte[] buffer = new byte[4096];
            int read = in.read(buffer, 0, 4096);

            while (read > -1) {
                md.update(buffer, 0, read);
                read = in.read(buffer, 0, 4096);
            }

            byte[] digest = md.digest();
            return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest)).toUpperCase();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}