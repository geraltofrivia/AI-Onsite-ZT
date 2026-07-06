// https://github.com/TeamNewPipe/NewPipe/tree/22a709d53b21cf7bed8487f6974786f60e831046/app/src/main/java/us/shandian/giga/io/ChunkFileInputStream.java#L69-L87
public class TempClass {
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if ((position + len) > length) {
            len = (int) (length - position);
        }
        if (len == 0) {
            return 0;
        }

        int res = source.read(b, off, len);
        position += res;

        if (onProgress != null && position > progressReport) {
            onProgress.report(position);
            progressReport = position + REPORT_INTERVAL;
        }

        return res;
    }

}