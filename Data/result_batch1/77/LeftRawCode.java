// https://github.com/mik3y/usb-serial-for-android/tree/c608aadc59d2d09f5e34ab928de42c16bf69864f/usbSerialForAndroid/src/main/java/com/hoho/android/usbserial/util/HexDump.java#L37-L77
public class TempClass {
    public static String dumpHexString(byte[] array, int offset, int length) {
        StringBuilder result = new StringBuilder();

        byte[] line = new byte[8];
        int lineIndex = 0;

        for (int i = offset; i < offset + length; i++) {
            if (lineIndex == line.length) {
                for (int j = 0; j < line.length; j++) {
                    if (line[j] > ' ' && line[j] < '~') {
                        result.append(new String(line, j, 1));
                    } else {
                        result.append(".");
                    }
                }

                result.append("\n");
                lineIndex = 0;
            }

            byte b = array[i];
            result.append(HEX_DIGITS[(b >>> 4) & 0x0F]);
            result.append(HEX_DIGITS[b & 0x0F]);
            result.append(" ");

            line[lineIndex++] = b;
        }

        for (int i = 0; i < (line.length - lineIndex); i++) {
            result.append("   ");
        }
        for (int i = 0; i < lineIndex; i++) {
            if (line[i] > ' ' && line[i] < '~') {
                result.append(new String(line, i, 1));
            } else {
                result.append(".");
            }
        }

        return result.toString();
    }

}