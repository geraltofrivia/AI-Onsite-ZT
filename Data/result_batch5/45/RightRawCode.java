// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/dracoon/src/main/java/ch/cyberduck/core/sds/triplecrypt/TripleCryptDecryptingInputStream.java#L62-L80
public class TempClass {
    @Override
    public int read(final byte[] dst, final int off, final int len) throws IOException {
        final ByteBuffer dest = ByteBuffer.allocate(len);
        int remaining = len;
        while(remaining > 0) {
            final int location = len - remaining;
            final int count = this.read(dest, off + location, remaining);
            if(IOUtils.EOF == count) {
                if(remaining == len) {
                    // nothing read before
                    return IOUtils.EOF;
                }
                break;
            }
            dest.get(dst, off + location, count);
            remaining -= count;
        }
        return len - remaining;
    }

}