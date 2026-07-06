// https://github.com/PojavLauncherTeam/PojavLauncher/tree/6eb830ba7aa8465d872a9ef0a8d87592c978e292/jre_lwjgl3glfw/src/main/java/android/util/MapCollections.java#L252-L262
public class TempClass {
        @Override
        public int hashCode() {
            int result = 0;
            for (int i=colGetSize()-1; i>=0; i--) {
                final Object key = colGetEntry(i, 0);
                final Object value = colGetEntry(i, 1);
                result += ( (key == null ? 0 : key.hashCode()) ^
                        (value == null ? 0 : value.hashCode()) );
            }
            return result;
        }

}