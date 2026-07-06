// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/memory/unsafe/utils/JavaUtils.java#L268-L294
public class TempClass {
  public static String bytesToString(long size) {
    long TB = 1L << 40;
    long GB = 1L << 30;
    long MB = 1L << 20;
    long KB = 1L << 10;
    double value = 0;
    String unit = null;

    if (size >= 2*TB) {
      value = size/TB;
      unit = "TB";
    } else if (size >= 2*GB) {
      value = size/GB;
      unit = "GB";
    } else if (size >= 2*MB) {
      value = size/MB;
      unit = "MB";
    } else if (size >= 2*KB) {
      value = size/KB;
      unit = "KB";
    } else {
      value = size;
      unit = "B";
    }

    return value + " " + unit;
  }

}