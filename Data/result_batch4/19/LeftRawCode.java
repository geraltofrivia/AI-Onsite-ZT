// https://github.com/redis/jedis/tree/8cdc90f7cd170cbda2a25778233489a779177650/src/main/java/redis/clients/jedis/util/RedisInputStream.java#L102-L135
public class TempClass {
  public byte[] readLineBytes() {

    /*
     * This operation should only require one fill. In that typical case we optimize allocation and
     * copy of the byte array. In the edge case where more than one fill is required then we take a
     * slower path and expand a byte array output stream as is necessary.
     */

    ensureFill();

    int pos = count;
    final byte[] buf = this.buf;
    while (true) {
      if (pos == limit) {
        return readLineBytesSlowly();
      }

      if (buf[pos++] == '\r') {
        if (pos == limit) {
          return readLineBytesSlowly();
        }

        if (buf[pos++] == '\n') {
          break;
        }
      }
    }

    final int N = (pos - count) - 2;
    final byte[] line = new byte[N];
    System.arraycopy(buf, count, line, 0, N);
    count = pos;
    return line;
  }

}