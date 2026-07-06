// https://github.com/SkyTubeTeam/SkyTube/tree/2c717ab729ce5f2fec648c93b04d933e870c71d1/app/src/main/java/free/rm/skytube/gui/activities/MainActivity.java#L384-L397
public class TempClass {
	private CharSequence getClipboardItem() {
		CharSequence clipboardText = null;
		ClipboardManager clipboardManager = ContextCompat.getSystemService(this, ClipboardManager.class);

		// if the clipboard contain data ...
		if (clipboardManager != null  &&  clipboardManager.hasPrimaryClip()) {
			ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);

			// gets the clipboard as text.
			clipboardText = item.coerceToText(this);
		}

		return clipboardText;
	}

}