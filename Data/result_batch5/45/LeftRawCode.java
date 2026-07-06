// https://github.com/jhy/jsoup/tree/35a41811a0b81b289f627b239ef9f568929281e3/src/main/java/org/jsoup/internal/SimpleBufferedInput.java#L42-L71
public class TempClass {
    @Override
    public int read(byte[] dest, int offset, int desiredLen) throws IOException {
        Validate.notNull(dest);
        if (offset < 0 || desiredLen < 0 || desiredLen > dest.length - offset) {
            throw new IndexOutOfBoundsException();
        } else if (desiredLen == 0) {
            return 0;
        }

        int bufAvail = bufLength - bufPos;
        if (bufAvail <= 0) { // can't serve from the buffer
            if (!inReadFully && bufMark < 0) {
                // skip creating / copying into a local buffer; just pass through
                int read = in.read(dest, offset, desiredLen);
                closeIfDone(read);
                return read;
            }
            fill();
            bufAvail = bufLength - bufPos;
        }

        int read = Math.min(bufAvail, desiredLen);
        if (read <= 0) {
            return -1;
        }

        System.arraycopy(getBuf(), bufPos, dest, offset, read);
        bufPos += read;
        return read;
    }

}