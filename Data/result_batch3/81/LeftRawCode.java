// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-core/src/main/java/software/coley/recaf/path/LineNumberPathNode.java#L55-L73
public class TempClass {
	@Override
	public int localCompare(PathNode<?> o) {
		if (this == o) return 0;

		if (o instanceof LineNumberPathNode lineNode) {
			int i = getValue().compareTo(lineNode.getValue());
			if (i == 0) {
				// Fall back to parent file comparison if the local line numbers are the same.
				// Not ideal, but we can't validate anything else here.
				FilePathNode parent = getParent();
				FilePathNode otherParent = lineNode.getParent();
				if (parent != null && otherParent != null)
					i = parent.localCompare(otherParent);
			}
			return i;
		}

		return 0;
	}

}