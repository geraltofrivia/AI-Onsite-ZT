// https://github.com/beemdevelopment/Aegis/tree/aa4877607dadf86c88c2f5a6cc3de6c869ff1132/app/src/main/java/com/beemdevelopment/aegis/helpers/SafHelper.java#L31-L46
public class TempClass {
    public static String getMimeType(Context context, Uri uri) {
        DocumentFile file = DocumentFile.fromSingleUri(context, uri);
        if (file != null) {
            String fileType = file.getType();
            if (fileType != null) {
                return fileType;
            }

            String ext = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            if (ext != null) {
                return MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
            }
        }

        return null;
    }

}