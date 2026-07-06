// https://github.com/react-native-image-picker/react-native-image-picker/tree/d2bc97ae73ef3e0e37c235dceb76f7bce9fa5f58/android/src/main/java/com/imagepicker/Utils.java#L411-L426
public class TempClass {
    static String getMimeType(Uri uri, Context context) {
        if (uri.getScheme().equals("file")) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
        } else if (uri.getScheme().equals("content")) {
            ContentResolver contentResolver = context.getContentResolver();
            String contentResolverMimeType = contentResolver.getType(uri);

            if (contentResolverMimeType.isBlank()) {
                return getMimeTypeForContent(uri, context);
            } else {
                return contentResolverMimeType;
            }
        }

        return "Unknown";
    }

}