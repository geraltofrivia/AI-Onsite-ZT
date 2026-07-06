// https://github.com/Konloch/bytecode-viewer/tree/903ac6a3fd19f1a3047789a91fb35e48d60459fb/src/main/java/the/bytecode/club/bytecodeviewer/util/MiscUtils.java#L337-L351
public class TempClass {
    public static void setLanguage(Language language)
    {
        Configuration.language = language;

        try
        {
            Language.ENGLISH.setLanguageTranslations(); //load english first incase the translation file is missing anything
            language.setLanguageTranslations(); //load translation file and swap text around as needed
            SwingUtilities.updateComponentTreeUI(BytecodeViewer.viewer);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}