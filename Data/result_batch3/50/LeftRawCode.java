// https://github.com/jhy/jsoup/tree/35a41811a0b81b289f627b239ef9f568929281e3/src/main/java/org/jsoup/parser/CharacterReader.java#L610-L628
public class TempClass {
    boolean containsIgnoreCase(String seq) {
        if (seq.equals(lastIcSeq)) {
            if (lastIcIndex == -1) return false;
            if (lastIcIndex >= bufPos) return true;
        }
        lastIcSeq = seq;

        String loScan = seq.toLowerCase(Locale.ENGLISH);
        int lo = nextIndexOf(loScan);
        if (lo > -1) {
            lastIcIndex = bufPos + lo; return true;
        }

        String hiScan = seq.toUpperCase(Locale.ENGLISH);
        int hi = nextIndexOf(hiScan);
        boolean found = hi > -1;
        lastIcIndex = found ? bufPos + hi : -1; // we don't care about finding the nearest, just that buf contains
        return found;
    }

}