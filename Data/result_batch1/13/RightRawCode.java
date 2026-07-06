// https://github.com/NeoApplications/Neo-Launcher/tree/bae394fac15c0eea0eb3d202e1272a504ee2b76b/src/com/android/launcher3/util/IOUtils.java#L53-L65
public class TempClass {
    public static long copy(InputStream from, OutputStream to) throws IOException {
        if (Utilities.ATLEAST_Q) {
            return FileUtils.copy(from, to);
        }
        byte[] buf = new byte[BUF_SIZE];
        long total = 0;
        int r;
        while ((r = from.read(buf)) != -1) {
            to.write(buf, 0, r);
            total += r;
        }
        return total;
    }

}