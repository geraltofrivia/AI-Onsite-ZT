// https://github.com/jhy/jsoup/tree/35a41811a0b81b289f627b239ef9f568929281e3/src/main/java/org/jsoup/internal/ControllableInputStream.java#L73-L108
public class TempClass {
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (readPos == 0) emitProgress(); // emits a progress

        boolean capped = maxSize != 0;
        if (interrupted || capped && remaining <= 0)
            return -1;
        if (Thread.currentThread().isInterrupted()) {
            // interrupted latches, because parse() may call twice
            interrupted = true;
            return -1;
        }

        if (capped && len > remaining)
            len = remaining; // don't read more than desired, even if available

        while (true) { // loop trying to read until we get some data or hit the overall timeout, if we have one
            if (expired())
                throw new SocketTimeoutException("Read timeout");

            try {
                final int read = super.read(b, off, len);
                if (read == -1) { // completed
                    contentLength = readPos;
                } else {
                    remaining -= read;
                    readPos += read;
                }
                emitProgress();
                return read;
            } catch (SocketTimeoutException e) {
                if (expired() || timeout == 0)
                    throw e;
            }
        }
    }

}