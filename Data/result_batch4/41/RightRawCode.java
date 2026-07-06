// https://github.com/hneemann/Digital/tree/0fc1087e8aef407dffda4846e2b5f1311e9180e2/src/main/java/de/neemann/digital/draw/graphics/SVGTokenizer.java#L83-L106
public class TempClass {
    private float parseNumber() throws TokenizerException {
        int p0 = pos;
        if (peekChar() == '+' || peekChar() == '-')
            pos++;

        while (pos < code.length() && (Character.isDigit(peekChar()) || peekChar() == '.'))
            pos++;

        if (pos < code.length() && (peekChar() == 'e' || peekChar() == 'E')) {
            pos++;
            if (peekChar() == '+' || peekChar() == '-' || Character.isDigit(peekChar())) {
                pos++;
                while (pos < code.length() && (Character.isDigit(peekChar()) || peekChar() == '.'))
                    pos++;
            } else
                pos--;
        }

        try {
            return Float.parseFloat(code.substring(p0, pos));
        } catch (NumberFormatException e) {
            throw new TokenizerException("not a number " + code.substring(p0, pos));
        }
    }

}