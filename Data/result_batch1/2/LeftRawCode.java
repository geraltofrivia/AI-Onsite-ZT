// https://github.com/scribejava/scribejava/tree/8970e8eeb0aff840d0b223890e9c392ee19218f7/scribejava-core/src/main/java/com/github/scribejava/core/utils/StreamUtils.java#L21-L35
public class TempClass {
    public static String getStreamContents(InputStream is) throws IOException {
        Preconditions.checkNotNull(is, "Cannot get String from a null object");
        final char[] buffer = new char[0x10000];
        final StringBuilder out = new StringBuilder();
        try (Reader in = new InputStreamReader(is, "UTF-8")) {
            int read;
            do {
                read = in.read(buffer, 0, buffer.length);
                if (read > 0) {
                    out.append(buffer, 0, read);
                }
            } while (read >= 0);
        }
        return out.toString();
    }

}