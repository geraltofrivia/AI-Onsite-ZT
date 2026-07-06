// https://github.com/qii/weiciyuan/tree/b8f4de854b12c4160a850c2217c779609c5eb445/src/org/qii/weiciyuan/support/imageutility/ImageUtility.java#L747-L762
public class TempClass {
    private static int[] calcResize(int actualWidth, int actualHeight, int reqWidth,
            int reqHeight) {

        int height = actualHeight;
        int width = actualWidth;

        float betweenWidth = ((float) reqWidth) / (float) actualWidth;
        float betweenHeight = ((float) reqHeight) / (float) actualHeight;

        float min = Math.min(betweenHeight, betweenWidth);

        height = (int) (min * actualHeight);
        width = (int) (min * actualWidth);

        return new int[]{width, height};
    }

}