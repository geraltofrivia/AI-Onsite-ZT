// https://github.com/scribejava/scribejava/tree/8970e8eeb0aff840d0b223890e9c392ee19218f7/scribejava-core/src/main/java/com/github/scribejava/core/model/Response.java#L129-L153
public class TempClass {
    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        IOException ioException = null;
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable == null) {
                    continue;
                }
                try {
                    closeable.close();
                } catch (IOException ioE) {
                    if (ioException != null) {
                        ioException = ioE;
                    }
                }
            }
        }
        if (ioException != null) {
            throw ioException;
        }
        closed = true;
    }

}