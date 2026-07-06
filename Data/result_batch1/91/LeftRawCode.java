// https://github.com/dreamhead/moco/tree/10f44fdb2d465ce27951e7b012e1ae59c8e13d89/moco-runner/src/main/java/com/github/dreamhead/moco/bootstrap/parser/HttpsArgsParser.java#L66-L78
public class TempClass {
    @Override
    protected Options options() {
        Options options = new Options();
        options.addOption(configOption());
        options.addOption(portOption());
        options.addOption(ShutdownPortOption.shutdownPortOption());
        options.addOption(settingsOption());
        options.addOption(envOption());
        options.addOption(httpsCertificate());
        options.addOption(keyStore());
        options.addOption(cert());
        return options;
    }

}