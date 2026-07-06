// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/SemanticVersion.java#L120-L135
public class TempClass {
	public SemanticVersion(String version) throws ParseException {
		vParts = new int[4];
		vParts[0] = vParts[1] = vParts[2] = vParts[3] = 0;
		preParts = new ArrayList<String>(5);
		metaParts = new ArrayList<String>(5);
		input = version.toCharArray();
		if (!stateMajor()) { // Start recursive descend
			throw new ParseException(version, errPos);
		}
		major = vParts[0];
		minor = vParts[1];
		patch = vParts[2];
		revision = vParts[3];
		preRelease = preParts.toArray(new String[preParts.size()]);
		buildMeta = metaParts.toArray(new String[metaParts.size()]);
	}

}