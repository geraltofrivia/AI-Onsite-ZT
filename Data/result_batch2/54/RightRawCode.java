// https://github.com/PojavLauncherTeam/PojavLauncher/tree/6eb830ba7aa8465d872a9ef0a8d87592c978e292/jre_lwjgl3glfw/src/main/java/android/util/MapCollections.java#L406-L420
public class TempClass {
        @Override
        public boolean removeAll(Collection<?> collection) {
            int N = colGetSize();
            boolean changed = false;
            for (int i=0; i<N; i++) {
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