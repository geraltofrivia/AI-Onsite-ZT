// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCLCore/src/main/java/org/jackhuang/hmcl/util/platform/hardware/CentralProcessor.java#L95-L112
public class TempClass {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(128);
        if (cores != null && cores.packages > 1)
            builder.append(cores.packages).append(" x ");

        builder.append(name);

        if (cores != null) {
            builder.append(" (");
            builder.append(cores.physical).append(" Cores");
            if (cores.logical > 0 && cores.logical != cores.physical)
                builder.append(" / ").append(cores.logical).append(" Threads");
            builder.append(")");
        }

        return builder.toString();
    }

}