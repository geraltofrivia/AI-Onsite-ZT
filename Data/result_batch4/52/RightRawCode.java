// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/dalvikdx/src/main/java/external/com/android/dx/util/ByteArrayAnnotatedOutput.java#L286-L308
public class TempClass {
    @Override
    public void write(byte[] bytes, int offset, int length) {
        int writeAt = cursor;
        int end = writeAt + length;
        int bytesEnd = offset + length;

        // twos-complement math trick: ((x < 0) || (y < 0)) <=> ((x|y) < 0)
        if (((offset | length | end) < 0) || (bytesEnd > bytes.length)) {
            throw new IndexOutOfBoundsException("bytes.length " +
                                                bytes.length + "; " +
                                                offset + "..!" + end);
        }

        if (stretchy) {
            ensureCapacity(end);
        } else if (end > data.length) {
            throwBounds();
            return;
        }

        System.arraycopy(bytes, offset, data, writeAt, length);
        cursor = end;
    }

}