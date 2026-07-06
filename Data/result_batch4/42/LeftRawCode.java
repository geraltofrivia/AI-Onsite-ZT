// https://github.com/rememberber/WePush/tree/2f5105fd8fea6d57e620d037a84128ae8fa8ff59/src/main/java/com/fangxuele/tool/push/ui/Init.java#L91-L168
public class TempClass {
    public static void initTheme() {
        try {
            switch (App.config.getTheme()) {
                case "系统默认":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case "Flat Light":
                    setAccentColor();
                    FlatLightLaf.setup();
                    break;
                case "Flat IntelliJ":
                    setAccentColor();
                    FlatIntelliJLaf.setup();
                    break;
                case "Flat Dark":
                    setAccentColor();
                    FlatDarkLaf.setup();
                    break;
                case "Dark purple":
                    FlatDarkPurpleIJTheme.setup();
                    break;
                case "IntelliJ Cyan":
                    FlatCyanLightIJTheme.setup();
                    break;
                case "IntelliJ Light":
                    FlatLightFlatIJTheme.setup();
                    break;
                case "Monocai":
                    FlatMonocaiIJTheme.setup();
                    break;
                case "Monokai Pro":
                    FlatMonokaiProIJTheme.setup();
                    UIManager.put("Button.arc", 5);
                    break;
                case "One Dark":
                    FlatOneDarkIJTheme.setup();
                    break;
                case "Gray":
                    FlatGrayIJTheme.setup();
                    break;
                case "High contrast":
                    FlatHighContrastIJTheme.setup();
                    break;
                case "GitHub Dark":
                    FlatGitHubDarkIJTheme.setup();
                    break;
                case "Xcode-Dark":
                    FlatXcodeDarkIJTheme.setup();
                    break;
                case "Vuesion":
                    FlatVuesionIJTheme.setup();
                    break;
                case "Flat macOS Light":
                    FlatMacLightLaf.setup();
                    break;
                case "Flat macOS Dark":
                    FlatMacDarkLaf.setup();
                    break;
                default:
                    setAccentColor();
                    FlatDarculaLaf.setup();
            }

            if (FlatLaf.isLafDark()) {
//                FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.brighter().brighter());
            } else {
                FlatSVGIcon.ColorFilter.getInstance().setMapper(color -> color.darker().darker());
//                SwingUtilities.windowForComponent(App.mainFrame).repaint();
            }

            if (App.config.isUnifiedBackground()) {
                UIManager.put("TitlePane.unifiedBackground", true);
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

}