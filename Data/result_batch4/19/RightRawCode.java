// https://github.com/fossasia/susi_server/tree/230d679cf18d0b6c1889cefd2cc1697810c8ec58/src/ai/susi/tools/BufferedRandomAccessFile.java#L232-L272
public class TempClass {
    private final byte[] getNextLine() throws IOException {
        if (buf_end - buf_pos <= 0) {
            if (fillBuffer() < 0) return null;
        }
        int lineend = -1;  // final position of the char considering \n

        for (int i = buf_pos; i < buf_end; i++) {
            if (buffer[i] == '\n') {
                lineend = i;
                break;
            }
            // check for only '\r' as line end
            if ((i - buf_pos > 0) && buffer[i - 1] == '\r') {
                lineend = i - 1;
                break;
            }
        }
        if (lineend < 0) {
            ByteBuffer line = new ByteBuffer();
            int c;
            int lastC = 0;
            while (((c = read()) != -1) && (c != '\n') && (lastC != '\r')) {
                line.append((char) c);
                lastC = c;
            }
            if (c == -1 && line.length() == 0) {line.close(); return new byte[0];}
            byte[] b = line.getBytes();
            line.close();
            return b;
        }
        byte[] b = null;
        if (lineend > 0 && buffer[lineend] == '\n' && buffer[lineend - 1] == '\r' && lineend - buf_pos - 1 >= 0) {
            b = new byte[lineend - buf_pos - 1];
            System.arraycopy(buffer, buf_pos, b, 0, lineend - buf_pos - 1);
        } else {
            b = new byte[lineend - buf_pos];
            System.arraycopy(buffer, buf_pos, b, 0, lineend - buf_pos);
        }
        buf_pos = lineend + 1;
        return b;
    }

}