// https://github.com/facebook/stetho/tree/2198797c0ff961dd2d9b87efa711a7d734c0e4d5/stetho/src/main/java/com/facebook/stetho/inspector/protocol/module/Runtime.java#L325-L343
public class TempClass {
    private List<?> arrayToList(Object object) {
      Class<?> type = object.getClass();
      if (!type.isArray()) {
        throw new IllegalArgumentException("Argument must be an array.  Was " + type);
      }
      Class<?> component = type.getComponentType();

      if (!component.isPrimitive()) {
        return Arrays.asList((Object[]) object);
      }

      // Loop manually for primitives.
      int length = Array.getLength(object);
      List<Object> ret = new ArrayList<>(length);
      for (int i = 0; i < length; i++) {
        ret.add(Array.get(object, i));
      }
      return ret;
    }

}