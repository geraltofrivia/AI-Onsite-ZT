// https://github.com/xuxueli/xxl-sso/tree/fe05e67df9debed0683414651ca6efcf28b53ecb/xxl-sso-core/src/main/java/com/xxl/sso/core/path/impl/AntPathMatcher.java#L704-L732
public class TempClass {
		public boolean matchStrings(String str, Map<String, String> uriTemplateVariables) {
			if (this.exactMatch) {
				return this.caseSensitive ? this.rawPattern.equals(str) : this.rawPattern.equalsIgnoreCase(str);
			}
			else if (this.pattern != null) {
				Matcher matcher = this.pattern.matcher(str);
				if (matcher.matches()) {
					if (uriTemplateVariables != null) {
						if (this.variableNames.size() != matcher.groupCount()) {
							throw new IllegalArgumentException("The number of capturing groups in the pattern segment " +
									this.pattern + " does not match the number of URI template variables it defines, " +
									"which can occur if capturing groups are used in a URI template regex. " +
									"Use non-capturing groups instead.");
						}
						for (int i = 1; i <= matcher.groupCount(); i++) {
							String name = this.variableNames.get(i - 1);
							if (name.startsWith("*")) {
								throw new IllegalArgumentException("Capturing patterns (" + name + ") are not " +
										"supported by the AntPathMatcher. Use the PathPatternParser instead.");
							}
							String value = matcher.group(i);
							uriTemplateVariables.put(name, value);
						}
					}
					return true;
				}
			}
			return false;
		}

}