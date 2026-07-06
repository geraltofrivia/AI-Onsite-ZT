// https://github.com/VonChange/utao/tree/127583941027a104cf7f39735f95e092a406a6a9/android/x5/app/src/main/java/tv/utao/x5/util/HttpRequest.java#L835-L858
public class TempClass {
  private static List<Object> arrayToList(final Object array) {
    if (array instanceof Object[])
      return Arrays.asList((Object[]) array);

    List<Object> result = new ArrayList<Object>();
    // Arrays of the primitive types can't be cast to array of Object, so this:
    if (array instanceof int[])
      for (int value : (int[]) array) result.add(value);
    else if (array instanceof boolean[])
      for (boolean value : (boolean[]) array) result.add(value);
    else if (array instanceof long[])
      for (long value : (long[]) array) result.add(value);
    else if (array instanceof float[])
      for (float value : (float[]) array) result.add(value);
    else if (array instanceof double[])
      for (double value : (double[]) array) result.add(value);
    else if (array instanceof short[])
      for (short value : (short[]) array) result.add(value);
    else if (array instanceof byte[])
      for (byte value : (byte[]) array) result.add(value);
    else if (array instanceof char[])
      for (char value : (char[]) array) result.add(value);
    return result;
  }

}