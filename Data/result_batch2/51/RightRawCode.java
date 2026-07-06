// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane_plugin_codeexplorer/src/main/java/org/freeplane/plugin/codeexplorer/task/CollectionAdapter.java#L17-L30
public class TempClass {
    @Override
    public JsonElement serialize(Collection<?> src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null || src.isEmpty()) // exclusion is made here
        return null;

      JsonArray array = new JsonArray();

      for (Object child : src) {
        JsonElement element = context.serialize(child);
        array.add(element);
      }

      return array;
    }

}