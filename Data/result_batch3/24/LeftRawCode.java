// https://github.com/facebook/fresco/tree/febd5f3074ce2adb82fb15e81f2a329554a093c8/fbcore/src/main/java/com/facebook/common/memory/PooledByteBufferInputStream.java#L88-L108
public class TempClass {
  @Override
  public int read(byte[] buffer, int offset, int length) {
    if (offset < 0 || length < 0 || offset + length > buffer.length) {
      throw new ArrayIndexOutOfBoundsException(
          "length=" + buffer.length + "; regionStart=" + offset + "; regionLength=" + length);
    }

    final int available = available();
    if (available <= 0) {
      return -1;
    }

    if (length <= 0) {
      return 0;
    }

    int numToRead = Math.min(available, length);
    mPooledByteBuffer.read(mOffset, buffer, offset, numToRead);
    mOffset += numToRead;
    return numToRead;
  }

}