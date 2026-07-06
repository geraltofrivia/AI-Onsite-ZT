// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/dracoon/src/main/java/ch/cyberduck/core/sds/io/swagger/client/model/ErrorResponse.java#L112-L125
public class TempClass {
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.code, errorResponse.code) &&
        Objects.equals(this.message, errorResponse.message) &&
        Objects.equals(this.debugInfo, errorResponse.debugInfo) &&
        Objects.equals(this.errorCode, errorResponse.errorCode);
  }

}