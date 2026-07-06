// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/core/dylib/src/main/java/ch/cyberduck/core/io/watchservice/FSEventWatchService.java#L155-L165
public class TempClass {
    private static Set<File> listFiles(final File folder) {
        Set<File> files = new HashSet<File>();
        if(folder.isDirectory()) {
            final File[] children = folder.listFiles();
            if(null == children) {
                return files;
            }
            Collections.addAll(files, children);
        }
        return files;
    }

}