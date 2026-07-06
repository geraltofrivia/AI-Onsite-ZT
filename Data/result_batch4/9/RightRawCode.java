// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/deepbox/src/main/java/ch/cyberduck/core/deepbox/io/swagger/client/model/Path.java#L61-L71
public class TempClass {
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Path path = (Path) o;
    return Objects.equals(this.segments, path.segments);
  }

}