// https://github.com/winder/Universal-G-Code-Sender/tree/b308753cee0f7b39ed0bbf4af959b27e19977198/ugs-platform/ugs-platform-plugin-jog/src/main/java/com/willwinder/ugs/nbp/jog/JogPanel.java#L415-L428
public class TempClass {
    @Override
    public void onSizeChange(int size) {
        switch (size) {
            case 0:
                setFontSize(FONT_SIZE_LABEL_SMALL);
                break;
            case 1:
                setFontSize(FONT_SIZE_LABEL_MEDIUM);
                break;
            default:
                setFontSize(FONT_SIZE_LABEL_LARGE);
                break;
        }
    }

}