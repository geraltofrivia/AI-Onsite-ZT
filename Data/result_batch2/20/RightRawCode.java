// https://github.com/siavash79/PixelXpert/tree/e9be11733bdcb20c1999901de4276d66f08bbcde/app/src/main/java/sh/siava/pixelxpert/ui/preferences/preferencesearch/SearchConfiguration.java#L139-L152
public class TempClass {
	private Bundle toBundle() {
		Bundle arguments = new Bundle();
		arguments.putParcelableArrayList(ARGUMENT_INDEX_FILES, filesToIndex);
		arguments.putParcelableArrayList(ARGUMENT_INDEX_INDIVIDUAL_PREFERENCES, preferencesToIndex);
		arguments.putBoolean(ARGUMENT_HISTORY_ENABLED, historyEnabled);
		arguments.putParcelable(ARGUMENT_REVEAL_ANIMATION_SETTING, revealAnimationSetting);
		arguments.putBoolean(ARGUMENT_FUZZY_ENABLED, fuzzySearchEnabled);
		arguments.putBoolean(ARGUMENT_BREADCRUMBS_ENABLED, breadcrumbsEnabled);
		arguments.putBoolean(ARGUMENT_SEARCH_BAR_ENABLED, searchBarEnabled);
		arguments.putString(ARGUMENT_TEXT_HINT, textHint);
		arguments.putString(ARGUMENT_TEXT_CLEAR_HISTORY, textClearHistory);
		arguments.putString(ARGUMENT_TEXT_NO_RESULTS, textNoResults);
		return arguments;
	}

}