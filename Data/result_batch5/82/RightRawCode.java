// https://github.com/winder/Universal-G-Code-Sender/tree/b308753cee0f7b39ed0bbf4af959b27e19977198/ugs-core/src/com/willwinder/universalgcodesender/utils/SemanticVersion.java#L41-L49
public class TempClass {
    public SemanticVersion(String version) throws ParseException {
        Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.find()) { // Start recursive descend
            throw new IllegalArgumentException(String.format("Could not parse version from string \"%s\"", version));
        }
        major = Integer.parseInt(StringUtils.defaultString(matcher.group("major"), "0"));
        minor = Integer.parseInt(StringUtils.defaultString(matcher.group("minor"), "0"));
        patch = Integer.parseInt(StringUtils.defaultString(matcher.group("patch"), "0"));
    }

}