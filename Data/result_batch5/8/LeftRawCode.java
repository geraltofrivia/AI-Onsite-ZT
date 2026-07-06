// https://github.com/WeiYe-Jing/datax-web/tree/f0aac36b6f3c5c6182b8985bd0bcf1470201e92f/datax-admin/src/main/java/com/wugui/datax/admin/util/ServletUtils.java#L102-L116
public class TempClass {
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}