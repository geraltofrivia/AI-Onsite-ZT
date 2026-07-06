// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/Functions.java#L2425-L2449
public class TempClass {
    public static String humanReadableByteSize(long size) {
        String measure = "B";
        if (size < 1024) {
            return size + " " + measure;
        }
        double number = size;
        if (number >= 1024) {
            number = number / 1024;
            measure = "KiB";
            if (number >= 1024) {
                number = number / 1024;
                measure = "MiB";
                if (number >= 1024) {
                    number = number / 1024;
                    measure = "GiB";
                    if (number >= 1024) {
                        number = number / 1024;
                        measure = "TiB";
                    }
                }
            }
        }
        DecimalFormat format = new DecimalFormat("#0.00");
        return format.format(number) + " " + measure;
    }

}