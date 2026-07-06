// https://github.com/react-native-image-picker/react-native-image-picker/tree/d2bc97ae73ef3e0e37c235dceb76f7bce9fa5f58/android/src/main/java/com/imagepicker/ImagePickerModuleImpl.java#L186-L229
public class TempClass {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

        // onActivityResult is called even when ActivityNotFoundException occurs
        if (!isValidRequestCode(requestCode) || (this.callback == null)) {
            return;
        }

        if (resultCode != Activity.RESULT_OK) {
            if (requestCode == REQUEST_LAUNCH_IMAGE_CAPTURE) {
                deleteFile(fileUri);
            }
            try {
                callback.invoke(getCancelMap());
                return;
            } catch (RuntimeException exception) {
                callback.invoke(getErrorMap(errOthers, exception.getMessage()));
            } finally {
                callback = null;
            }
        }

        switch (requestCode) {
            case REQUEST_LAUNCH_IMAGE_CAPTURE:
                if (options.saveToPhotos) {
                    saveToPublicDirectory(cameraCaptureURI, reactContext, "photo");
                }

                onAssetsObtained(Collections.singletonList(fileUri));
                break;

            case REQUEST_LAUNCH_LIBRARY:
                onAssetsObtained(collectUrisFromData(data));
                break;

            case REQUEST_LAUNCH_VIDEO_CAPTURE:
                if (options.saveToPhotos) {
                    saveToPublicDirectory(cameraCaptureURI, reactContext, "video");
                }

                onAssetsObtained(Collections.singletonList(fileUri));
                break;
        }
    }

}