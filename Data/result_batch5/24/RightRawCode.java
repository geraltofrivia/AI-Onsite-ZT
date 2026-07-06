// https://github.com/siavash79/PixelXpert/tree/e9be11733bdcb20c1999901de4276d66f08bbcde/app/src/main/java/sh/siava/pixelxpert/ui/preferences/preferencesearch/SearchPreferenceFragment.java#L173-L184
public class TempClass {
	private void loadHistory() {
		history = new ArrayList<>();
		if (!searchConfiguration.isHistoryEnabled()) {
			return;
		}

		int size = prefs.getInt("history_size", 0);
		for (int i = 0; i < size; i++) {
			String title = prefs.getString("history_" + i, null);
			history.add(new HistoryItem(title));
		}
	}

}