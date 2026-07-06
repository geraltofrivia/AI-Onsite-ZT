// https://github.com/Piasy/BigImageViewer/tree/067f8eb86ee522190ab473f36a1b0de11c21a663/BigImageViewer/src/main/java/com/github/piasy/biv/metadata/ImageInfoExtractor.java#L55-L79
public class TempClass {
    public static int getImageType(File file) {
        int type = TYPE_STILL_IMAGE;
        try {
            FileInputStream inputStream = new FileInputStream(file);

            byte[] header = new byte[21];
            int read = inputStream.read(header);
            if (read >= 3 && isGifHeader(header)) {
                type = TYPE_GIF;
            } else if (read >= 12 && isWebpHeader(header)) {
                if (read >= 17 && isExtendedWebp(header)
                    && (header[20] & ANIMATED_WEBP_MASK) != 0) {
                    type = TYPE_ANIMATED_WEBP;
                } else {
                    type = TYPE_STILL_WEBP;
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return type;
    }

}