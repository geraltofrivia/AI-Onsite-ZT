// https://github.com/ximsfei/Android-skin-support/tree/3eaa81f5999342018186fb28a2d915fc8605e4c4/androidx/skin-support/src/main/java/skin/support/collection/MapCollections.java#L392-L406
public class TempClass {
        @Override
        public boolean removeAll(Collection<?> collection) {
            int N = colGetSize();
            boolean changed = false;
            for (int i = 0; i < N; i++) {
                Object cur = colGetEntry(i, 1);
                if (collection.contains(cur)) {
                    colRemoveAt(i);
                    i--;
                    N--;
                    changed = true;
                }
            }
            return changed;
        }

}