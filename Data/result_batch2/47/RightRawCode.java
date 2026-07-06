// https://github.com/xuxueli/xxl-sso/tree/fe05e67df9debed0683414651ca6efcf28b53ecb/xxl-sso-core/src/main/java/com/xxl/sso/core/path/impl/AntPathMatcher.java#L325-L339
public class TempClass {
	private boolean isPotentialMatch(String path, String[] pattDirs) {
		if (!this.trimTokens) {
			int pos = 0;
			for (String pattDir : pattDirs) {
				int skipped = skipSeparator(path, pos, this.pathSeparator);
				pos += skipped;
				skipped = skipSegment(path, pos, pattDir);
				if (skipped < pattDir.length()) {
					return (skipped > 0 || (pattDir.length() > 0 && isWildcardChar(pattDir.charAt(0))));
				}
				pos += skipped;
			}
		}
		return true;
	}

}