// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/SemanticVersion.java#L297-L331
public class TempClass {
	@Override
	public int compareTo(SemanticVersion v) {
		int result = major - v.major;
		if (result == 0) { // Same major
			result = minor - v.minor;
			if (result == 0) { // Same minor
				result = patch - v.patch;
				if (result == 0) { // Same patch
					result = revision - v.revision;
					if (result == 0) { // Same revision
						if (preRelease.length == 0 && v.preRelease.length > 0) {
							result = 1; // No pre release wins over pre release
						}
						if (v.preRelease.length == 0 && preRelease.length > 0) {
							result = -1; // No pre release wins over pre release
						}
						if (preRelease.length > 0 && v.preRelease.length > 0) {
							int len = Math.min(preRelease.length, v.preRelease.length);
							int count = 0;
							for (count = 0; count < len; count++) {
								result = comparePreReleaseTag(count, v);
								if (result != 0) {
									break;
								}
							}
							if (result == 0 && count == len) { // Longer version wins.
								result = preRelease.length - v.preRelease.length;
							}
						}
					}
				}
			}
		}
		return result;
	}

}