// https://github.com/dreamhead/moco/tree/10f44fdb2d465ce27951e7b012e1ae59c8e13d89/moco-core/src/main/java/com/github/dreamhead/moco/util/AntPathMatcher.java#L422-L442
public class TempClass {
    public String extractPathWithinPattern(String pattern, String path) {
        String[] patternParts = Strings.tokenizeToStringArray(pattern, this.pathSeparator, this.trimTokens, true);
        String[] pathParts = Strings.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);
        StringBuilder builder = new StringBuilder();
        boolean pathStarted = false;

        for (int segment = 0; segment < patternParts.length; segment++) {
            String patternPart = patternParts[segment];
            if (patternPart.indexOf('*') > -1 || patternPart.indexOf('?') > -1) {
                for (; segment < pathParts.length; segment++) {
                    if (pathStarted || (segment == 0 && !pattern.startsWith(this.pathSeparator))) {
                        builder.append(this.pathSeparator);
                    }
                    builder.append(pathParts[segment]);
                    pathStarted = true;
                }
            }
        }

        return builder.toString();
    }

}