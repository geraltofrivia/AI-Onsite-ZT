// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/SemanticVersion.java#L266-L295
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
		if (ov.major != major || ov.minor != minor || ov.patch != patch || ov.revision != revision) {
			return false;
		}
		if (ov.preRelease.length != preRelease.length) {
			return false;
		}
		for (int i = 0; i < preRelease.length; i++) {
			if (!preRelease[i].equals(ov.preRelease[i])) {
				return false;
			}
		}
		if (ov.buildMeta.length != buildMeta.length) {
			return false;
		}
		for (int i = 0; i < buildMeta.length; i++) {
			if (!buildMeta[i].equals(ov.buildMeta[i])) {
				return false;
			}
		}
		return true;
	}

}