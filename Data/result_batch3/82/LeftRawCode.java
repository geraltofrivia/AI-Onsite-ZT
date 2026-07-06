// https://github.com/TooTallNate/Java-WebSocket/tree/8c5766a293c2dd3e0d035c0e0d70f88f57235fa8/src/main/java/org/java_websocket/util/Base64.java#L802-L871
public class TempClass {
  private static int decode4to3(
      byte[] source, int srcOffset,
      byte[] destination, int destOffset, int options) {

    // Lots of error checking and exception throwing
    if (source == null) {
      throw new IllegalArgumentException("Source array was null.");
    }   // end if
    if (destination == null) {
      throw new IllegalArgumentException("Destination array was null.");
    }   // end if
    if (srcOffset < 0 || srcOffset + 3 >= source.length) {
      throw new IllegalArgumentException(String.format(
          "Source array with length %d cannot have offset of %d and still process four bytes.",
          source.length, srcOffset));
    }   // end if
    if (destOffset < 0 || destOffset + 2 >= destination.length) {
      throw new IllegalArgumentException(String.format(
          "Destination array with length %d cannot have offset of %d and still store three bytes.",
          destination.length, destOffset));
    }   // end if

    final byte[] DECODABET = getDecodabet(options);

    // Example: Dk==
    if (source[srcOffset + 2] == EQUALS_SIGN) {
      // Two ways to do the same thing. Don't know which way I like best.
      //int outBuff =   ( ( DECODABET[ source[ srcOffset    ] ] << 24 ) >>>  6 )
      //              | ( ( DECODABET[ source[ srcOffset + 1] ] << 24 ) >>> 12 );
      int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18)
          | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12);

      destination[destOffset] = (byte) (outBuff >>> 16);
      return 1;
    }

    // Example: DkL=
    else if (source[srcOffset + 3] == EQUALS_SIGN) {
      // Two ways to do the same thing. Don't know which way I like best.
      //int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] << 24 ) >>>  6 )
      //              | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
      //              | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 );
      int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18)
          | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12)
          | ((DECODABET[source[srcOffset + 2]] & 0xFF) << 6);

      destination[destOffset] = (byte) (outBuff >>> 16);
      destination[destOffset + 1] = (byte) (outBuff >>> 8);
      return 2;
    }

    // Example: DkLE
    else {
      // Two ways to do the same thing. Don't know which way I like best.
      //int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] << 24 ) >>>  6 )
      //              | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
      //              | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 )
      //              | ( ( DECODABET[ source[ srcOffset + 3 ] ] << 24 ) >>> 24 );
      int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18)
          | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12)
          | ((DECODABET[source[srcOffset + 2]] & 0xFF) << 6)
          | ((DECODABET[source[srcOffset + 3]] & 0xFF));

      destination[destOffset] = (byte) (outBuff >> 16);
      destination[destOffset + 1] = (byte) (outBuff >> 8);
      destination[destOffset + 2] = (byte) (outBuff);

      return 3;
    }
  }   // end decodeToBytes

}