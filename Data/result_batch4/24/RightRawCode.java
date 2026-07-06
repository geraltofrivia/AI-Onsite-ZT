// https://github.com/beemdevelopment/Aegis/tree/aa4877607dadf86c88c2f5a6cc3de6c869ff1132/app/src/main/java/com/beemdevelopment/aegis/ui/models/ErrorCardInfo.java#L31-L43
public class TempClass {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ErrorCardInfo)) {
            return false;
        }

        // This equality check purposefully ignores the onclick listener
        ErrorCardInfo info = (ErrorCardInfo) o;
        return Objects.equals(getMessage(), info.getMessage());
    }

}