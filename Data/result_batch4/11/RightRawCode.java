// https://github.com/Team-xManager/xManager/tree/32d3d045a277618901eb4b720cd137b0605e44e3/app/src/main/java/com/xc3fff0e/xmanager/FileUtil.java#L564-L588
public class TempClass {
    public static int getJpegRotate(String filePath) {
        int rotate = 0;
        try {
            ExifInterface exif = new ExifInterface(filePath);
            int iOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

            switch (iOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }
        } catch (IOException e) {
            return 0;
        }

        return rotate;
    }

}