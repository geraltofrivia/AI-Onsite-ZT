// https://github.com/winder/Universal-G-Code-Sender/tree/b308753cee0f7b39ed0bbf4af959b27e19977198/ugs-core/src/com/willwinder/universalgcodesender/utils/SemanticVersion.java#L79-L89
public class TempClass {
    @Override
    public int compareTo(SemanticVersion v) {
        int result = major - v.major;
        if (result == 0) { // Same major
            result = minor - v.minor;
            if (result == 0) { // Same minor
                result = patch - v.patch;
            }
        }
        return result;
    }

}