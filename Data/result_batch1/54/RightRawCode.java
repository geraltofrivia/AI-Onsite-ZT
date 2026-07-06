// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-instrument-client/src/main/java/qunar/tc/bistoury/instrument/client/spring/el/MethodReference.java#L222-L234
public class TempClass {
    @Override
    public String toStringAST() {
        StringBuilder sb = new StringBuilder(this.name);
        sb.append("(");
        for (int i = 0; i < getChildCount(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(getChild(i).toStringAST());
        }
        sb.append(")");
        return sb.toString();
    }

}