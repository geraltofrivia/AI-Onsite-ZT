// https://github.com/chrisk44/Hijacker/tree/898af5b530dd000b8251f21da94626d9e41e53d2/app/src/main/java/com/hijacker/BoundedBufferedReader.java#L282-L343
public class TempClass {
    @Override
    public int read(char[] buffer, int offset, int length) throws IOException {
        synchronized (lock) {
            checkNotClosed();
            checkOffsetAndCount(buffer.length, offset, length);
            if (length == 0) {
                return 0;
            }

            maybeSwallowLF();

            int outstanding = length;
            while (outstanding > 0) {
                // If there are chars in the buffer, grab those first.
                int available = end - pos;
                if (available > 0) {
                    int count = available >= outstanding ? outstanding : available;
                    System.arraycopy(buf, pos, buffer, offset, count);
                    pos += count;
                    offset += count;
                    outstanding -= count;
                }

                /*
                 * Before attempting to read from the underlying stream, make
                 * sure we really, really want to. We won't bother if we're
                 * done, or if we've already got some chars and reading from the
                 * underlying stream would block.
                 */
                if (outstanding == 0 || (outstanding < length && !in.ready())) {
                    break;
                }

                // assert(pos == end);

                /*
                 * If we're unmarked and the requested size is greater than our
                 * buffer, read the chars directly into the caller's buffer. We
                 * don't read into smaller buffers because that could result in
                 * a many reads.
                 */
                if ((mark == -1 || (pos - mark >= markLimit)) && outstanding >= buf.length) {
                    int count = in.read(buffer, offset, outstanding);
                    if (count > 0) {
                        outstanding -= count;
                        mark = -1;
                    }
                    break; // assume the source stream gave us all that it could
                }

                if (fillBuf() == -1) {
                    break; // source is exhausted
                }
            }

            int count = length - outstanding;
            if (count > 0) {
                return count;
            }
            return -1;
        }
    }

}