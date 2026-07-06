// https://github.com/CrawlScript/WebCollector/tree/878704586e75ac49737d06fc527f49c6b3b8b9b2/src/main/java/cn/edu/hfut/dmic/webcollector/util/MD5Utils.java#L32-L41
public class TempClass {
    public static String md5(byte[] original) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original);
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

}