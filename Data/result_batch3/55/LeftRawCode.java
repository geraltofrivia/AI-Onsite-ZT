// https://github.com/jhy/jsoup/tree/35a41811a0b81b289f627b239ef9f568929281e3/src/test/java/org/jsoup/internal/StringUtilTest.java#L169-L183
public class TempClass {
    @Test void isAsciiLetter() {
        assertTrue(StringUtil.isAsciiLetter('a'));
        assertTrue(StringUtil.isAsciiLetter('n'));
        assertTrue(StringUtil.isAsciiLetter('z'));
        assertTrue(StringUtil.isAsciiLetter('A'));
        assertTrue(StringUtil.isAsciiLetter('N'));
        assertTrue(StringUtil.isAsciiLetter('Z'));

        assertFalse(StringUtil.isAsciiLetter(' '));
        assertFalse(StringUtil.isAsciiLetter('-'));
        assertFalse(StringUtil.isAsciiLetter('0'));
        assertFalse(StringUtil.isAsciiLetter('ß'));
        assertFalse(StringUtil.isAsciiLetter('Ě'));
    }


}