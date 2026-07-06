// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/features/link/HyperTextLinkModel.java#L40-L50
public class TempClass {
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + getSource().hashCode();
	    final String targetID = getTargetID();
	    if(targetID == null)
	    	return result;
		result = prime * result + targetID.hashCode();
		return result;
    }

}