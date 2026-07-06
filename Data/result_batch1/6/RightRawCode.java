// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/dracoon/src/main/java/ch/cyberduck/core/sds/io/swagger/client/model/RoleList.java#L57-L67
public class TempClass {
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleList roleList = (RoleList) o;
    return Objects.equals(this.items, roleList.items);
  }

}