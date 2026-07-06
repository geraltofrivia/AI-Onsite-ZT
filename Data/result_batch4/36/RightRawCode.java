// https://github.com/CrawlScript/WebCollector/tree/878704586e75ac49737d06fc527f49c6b3b8b9b2/src/main/java/cn/edu/hfut/dmic/webcollector/util/FileUtils.java#L33-L43
public class TempClass {
    public static void deleteDir(File dir) {
        File[] filelist = dir.listFiles();
        for (File file : filelist) {
            if (file.isFile()) {
                file.delete();
            } else {
                deleteDir(file);
            }
        }
        dir.delete();
    }

}