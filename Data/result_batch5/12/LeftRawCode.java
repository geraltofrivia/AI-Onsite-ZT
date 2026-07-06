// https://github.com/react-native-image-picker/react-native-image-picker/tree/d2bc97ae73ef3e0e37c235dceb76f7bce9fa5f58/android/src/main/java/com/imagepicker/Utils.java#L275-L294
public class TempClass {
    static int[] getImageDimensBasedOnConstraints(int origWidth, int origHeight, Options options) {
        int width = origWidth;
        int height = origHeight;

        if (options.maxWidth == 0 || options.maxHeight == 0) {
            return new int[]{width, height};
        }

        if (options.maxWidth < width) {
            height = (int) (((float) options.maxWidth / width) * height);
            width = options.maxWidth;
        }

        if (options.maxHeight < height) {
            width = (int) (((float) options.maxHeight / height) * width);
            height = options.maxHeight;
        }

        return new int[]{width, height};
    }

}