// https://github.com/winder/Universal-G-Code-Sender/tree/b308753cee0f7b39ed0bbf4af959b27e19977198/ugs-core/src/com/willwinder/universalgcodesender/utils/SemanticVersion.java#L67-L77
public class TempClass {
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SemanticVersion)) {
            return false;
        }
        SemanticVersion ov = (SemanticVersion) other;
        return ov.major == major && ov.minor == minor && ov.patch == patch;
    }

}