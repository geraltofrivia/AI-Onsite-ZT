// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/core/src/main/java/ch/cyberduck/core/transfer/TransferProgress.java#L61-L70
public class TempClass {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransferProgress{");
        sb.append("size=").append(size);
        sb.append(", transferred=").append(transferred);
        sb.append(", progress='").append(progress).append('\'');
        sb.append(", speed=").append(speed);
        sb.append('}');
        return sb.toString();
    }

}