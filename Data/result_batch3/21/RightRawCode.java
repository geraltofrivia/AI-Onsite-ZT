// https://github.com/open-keychain/open-keychain/tree/8d0bd1f8537fb3a795fda8161f730a313ef7b01c/OpenKeychain/src/main/java/org/sufficientlysecure/keychain/compatibility/ClipboardReflection.java#L31-L53
public class TempClass {
    @Nullable
    public static String getClipboardText(@Nullable Context context) {
        if (context == null) {
            return null;
        }
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) {
            return null;
        }

        ClipData clip = clipboard.getPrimaryClip();
        if (clip == null || clip.getItemCount() == 0) {
            Timber.e("No clipboard data!");
            return null;
        }

        ClipData.Item item = clip.getItemAt(0);
        CharSequence seq = item.coerceToText(context);
        if (seq != null) {
            return seq.toString();
        }
        return null;
    }

}