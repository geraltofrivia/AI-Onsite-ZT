// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-instrument-client/src/main/java/qunar/tc/bistoury/instrument/client/location/ResourcesDatabase.java#L185-L234
public class TempClass {
            @Override
            public Iterator<Directory> iterator() {
                return new Iterator<Directory>() {
                    private Directory next = getRoot();

                    @Override
                    public boolean hasNext() {
                        return next != null;
                    }

                    @Override
                    public Directory next() {
                        if (next == null) {
                            throw new NoSuchElementException();
                        }

                        Directory rc = next;

                        if (next.childrenIds.length > 0) {  // First child.
                            next = getDirectory(next.childrenIds[0]);
                        } else {  // Next sibling (if any).
                            if (next.isRoot()) {
                                next = null;
                            }

                            while (next != null) {
                                Directory parent = getDirectory(next.getParentId());
                                int i = 0;
                                while (parent.childrenIds[i] != next.id) {
                                    ++i;
                                }

                                if (i < parent.childrenIds.length - 1) {
                                    next = getDirectory(parent.childrenIds[i + 1]);
                                    break;
                                }

                                next = (parent.isRoot() ? null : parent);
                            }
                        }

                        return rc;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

}