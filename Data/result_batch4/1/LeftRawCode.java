// https://github.com/Bearded-Hen/Android-Bootstrap/tree/b3d62cc1847e26d420c53c92665a4fe1e6ee7ecf/sample/src/main/java/com/fractalwrench/androidbootstrap/sample/BootstrapEditTextExample.java#L65-L84
public class TempClass {
    @OnClick(R.id.bedit_text_change_size_btn) void onSizeExampleClicked() {
        switch (size) {
            case XS:
                size = DefaultBootstrapSize.SM;
                break;
            case SM:
                size = DefaultBootstrapSize.MD;
                break;
            case MD:
                size = DefaultBootstrapSize.LG;
                break;
            case LG:
                size = DefaultBootstrapSize.XL;
                break;
            case XL:
                size = DefaultBootstrapSize.XS;
                break;
        }
        sizeExample.setBootstrapSize(size);
    }

}