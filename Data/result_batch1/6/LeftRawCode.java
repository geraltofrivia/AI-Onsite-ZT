// https://github.com/facebook/stetho/tree/2198797c0ff961dd2d9b87efa711a7d734c0e4d5/stetho/src/test/java/com/facebook/stetho/json/ObjectMapperTest.java#L248-L269
public class TempClass {
    @Override
    public boolean equals(Object o) {
      if (o == null || !(o instanceof JsonPropertyStringList)) {
        return false;
      }
      JsonPropertyStringList rhs = (JsonPropertyStringList) o;
      if (stringList == null || rhs.stringList == null) {
        return stringList == rhs.stringList;
      }
      if (stringList.size() != rhs.stringList.size()) {
        return false;
      }
      ListIterator<String> myIter = stringList.listIterator();
      ListIterator<String> rhsIter = rhs.stringList.listIterator();
      while (myIter.hasNext()) {
        if (!Objects.equals(myIter.next(), rhsIter.next())) {
          return false;
        }
      }

      return true;
    }

}