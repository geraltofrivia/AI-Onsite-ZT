// https://github.com/redis/jedis/tree/8cdc90f7cd170cbda2a25778233489a779177650/src/main/java/redis/clients/jedis/CommandObject.java#L34-L57
public class TempClass {
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CommandObject)) {
      return false;
    }

    Iterator<Rawable> e1 = arguments.iterator();
    Iterator<Rawable> e2 = ((CommandObject) o).arguments.iterator();
    while (e1.hasNext() && e2.hasNext()) {
      Rawable o1 = e1.next();
      Rawable o2 = e2.next();
      if (!(o1 == null ? o2 == null : o1.equals(o2))) {
        return false;
      }
    }
    if (e1.hasNext() || e2.hasNext()) {
      return false;
    }

    return builder == ((CommandObject) o).builder;
  }

}