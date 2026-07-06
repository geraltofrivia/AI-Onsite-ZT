// https://github.com/JabRef/jabref/tree/6acae662af7a64d25c15dd23dc2f2b5e195be7ec/jablib/src/main/java/org/jabref/logic/l10n/Localization.java#L65-L88
public class TempClass {
    public static void setLanguage(Language language) {
        Optional<Locale> knownLanguage = Language.convertToSupportedLocale(language);
        final Locale defaultLocale = Locale.getDefault();
        if (knownLanguage.isEmpty()) {
            LoggerFactory.getLogger(Localization.class).warn("Language {} is not supported by JabRef (Default: {})", language, defaultLocale);
            setLanguage(Language.ENGLISH);
            return;
        }
        // avoid reinitialization of the language bundles
        final Locale langLocale = knownLanguage.get();
        if ((locale != null) && locale.equals(langLocale) && locale.equals(defaultLocale)) {
            return;
        }
        locale = langLocale;
        Locale.setDefault(locale);

        try {
            createResourceBundles(locale);
        } catch (MissingResourceException ex) {
            // should not happen as we have scripts to enforce this
            LoggerFactory.getLogger(Localization.class).warn("Could not find bundles for language {}, switching to full english language", locale, ex);
            setLanguage(Language.ENGLISH);
        }
    }

}