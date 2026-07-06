// https://github.com/xuxueli/xxl-sso/tree/fe05e67df9debed0683414651ca6efcf28b53ecb/xxl-sso-core/src/main/java/com/xxl/sso/core/path/impl/AntPathMatcher.java#L341-L357
public class TempClass {
	private int skipSegment(String path, int pos, String prefix) {
		int skipped = 0;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (isWildcardChar(c)) {
				return skipped;
			}
			int currPos = pos + skipped;
			if (currPos >= path.length()) {
				return 0;
			}
			if (c == path.charAt(currPos)) {
				skipped++;
			}
		}
		return skipped;
	}

}