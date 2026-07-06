// https://github.com/f-droid/fdroidclient/tree/83f87606b0558083b06691ac391d41c0f52bdbb8/app/src/full/java/kellinwood/zipio/ZipOutput.java#L106-L114
public class TempClass {
    public void writeInt(int value) throws IOException {
        byte[] data = new byte[4];
        for (int i = 0; i < 4; i++) {
            data[i] = (byte) (value & 0xFF);
            value = value >> 8;
        }
        out.write(data);
        filePointer += 4;
    }

}