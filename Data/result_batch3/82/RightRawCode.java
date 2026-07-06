// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-serverside-common/src/main/java/qunar/tc/bistoury/serverside/agile/Base64.java#L355-L388
public class TempClass {
	private static int decode4to3(byte[] source, int srcOffset,
			byte[] destination, int destOffset, byte[] decodabet) {
		// Example: Dk==
		if (source[srcOffset + 2] == EQUALS_SIGN) {
			int outBuff =
				((decodabet[source[srcOffset]] << 24) >>> 6)
				| ((decodabet[source[srcOffset + 1]] << 24) >>> 12);

			destination[destOffset] = (byte) (outBuff >>> 16);
			return 1;
		} else if (source[srcOffset + 3] == EQUALS_SIGN) {
			// Example: DkL=
			int outBuff =
				((decodabet[source[srcOffset]] << 24) >>> 6)
				| ((decodabet[source[srcOffset + 1]] << 24) >>> 12)
				| ((decodabet[source[srcOffset + 2]] << 24) >>> 18);

			destination[destOffset] = (byte) (outBuff >>> 16);
			destination[destOffset + 1] = (byte) (outBuff >>> 8);
			return 2;
		} else {
			// Example: DkLE
			int outBuff =
				((decodabet[source[srcOffset]] << 24) >>> 6)
				| ((decodabet[source[srcOffset + 1]] << 24) >>> 12)
				| ((decodabet[source[srcOffset + 2]] << 24) >>> 18)
				| ((decodabet[source[srcOffset + 3]] << 24) >>> 24);

			destination[destOffset] = (byte) (outBuff >> 16);
			destination[destOffset + 1] = (byte) (outBuff >> 8);
			destination[destOffset + 2] = (byte) (outBuff);
			return 3;
		}
	} // end decodeToBytes

}