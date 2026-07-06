// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/SemanticVersion.java#L228-L259
public class TempClass {
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(major);
		ret.append('.');
		ret.append(minor);
		ret.append('.');
		ret.append(patch);
		if (revision != 0) {
			ret.append('.');
			ret.append(revision);
		}
		if (preRelease.length > 0) {
			ret.append('-');
			for (int i = 0; i < preRelease.length; i++) {
				ret.append(preRelease[i]);
				if (i < preRelease.length - 1) {
					ret.append('.');
				}
			}
		}
		if (buildMeta.length > 0) {
			ret.append('+');
			for (int i = 0; i < buildMeta.length; i++) {
				ret.append(buildMeta[i]);
				if (i < buildMeta.length - 1) {
					ret.append('.');
				}
			}
		}
		return ret.toString();
	}

}