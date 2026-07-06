// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/features/map/NodeRelativePath.java#L41-L52
public class TempClass {
			@Override
			public int compare(NodeModel o1, NodeModel o2) {
				if(o1 == o2)
					return 0;
				if(o1 == null)
					return -1;
				if(o2 == null)
					return 1;
				final NodeAbsolutePath absoluteBeginPath = getPath(o1);
				final NodeAbsolutePath absoluteEndPath = getPath(o2);
				return new NodeRelativePath(absoluteBeginPath, absoluteEndPath).compareNodePositions();
			}

}