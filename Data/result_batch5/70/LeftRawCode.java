// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-extensions/mockito-android/src/main/java/org/mockito/android/internal/creation/AndroidTempFileLocator.java#L60-L87
public class TempClass {
    private static File[] guessPath(String input) {
        List<File> results = new ArrayList<File>();
        for (String potential : splitPathList(input)) {
            if (!potential.startsWith("/data/app/")) {
                continue;
            }
            int start = "/data/app/".length();
            int end = potential.lastIndexOf(".apk");
            if (end != potential.length() - 4) {
                continue;
            }
            int dash = potential.indexOf("-");
            if (dash != -1) {
                end = dash;
            }
            String packageName = potential.substring(start, end);
            File dataDir = new File("/data/data/" + packageName);
            if (isWritableDirectory(dataDir)) {
                File cacheDir = new File(dataDir, "cache");
                if (fileOrDirExists(cacheDir) || cacheDir.mkdir()) {
                    if (isWritableDirectory(cacheDir)) {
                        results.add(cacheDir);
                    }
                }
            }
        }
        return results.toArray(new File[results.size()]);
    }

}