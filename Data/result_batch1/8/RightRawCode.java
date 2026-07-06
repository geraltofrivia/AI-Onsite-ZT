// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/core/ui/menubuilders/generic/Entry.java#L173-L182
public class TempClass {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((builders == null) ? 0 : builders.hashCode());
		result = prime * result + ((childEntries == null) ? 0 : childEntries.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}