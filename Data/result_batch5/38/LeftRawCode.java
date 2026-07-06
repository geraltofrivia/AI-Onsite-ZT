// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-plugin/server-plugin-pack-gem/src/main/java/io/onedev/server/plugin/pack/gem/marshal/Marshaller.java#L172-L189
public class TempClass {
	private void writeInt(int value) throws IOException {
		if (value == 0) {
			stream.write(0);
		} else if (0 < value && value < 123) {
			stream.write(value + 5);
		} else if (-124 < value && value < 0) {
			stream.write((value - 5) & 0xff);
		} else {
			byte[] buf = new byte[4];
			int i = 0;
			do {
				buf[i++] = (byte) (value & 0xff);
				value >>= 8;
			} while (i < buf.length && value != 0 && value != -1);
			stream.write(value < 0 ? -i : i);
			stream.write(buf, 0, i);
		}
	}

}