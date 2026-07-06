// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/dexmaker/src/main/java/external/com/android/dx/AppDataDirGuesser.java#L137-L182
public class TempClass {
    File[] guessPath(String input) {
        List<File> results = new ArrayList<>();
        String apkPathRoot = "/data/app/";
        for (String potential : splitPathList(input)) {
            if (!potential.startsWith(apkPathRoot)) {
                continue;
            }
            int end = potential.lastIndexOf(".apk");
            if (end != potential.length() - 4) {
                continue;
            }
            int endSlash = potential.lastIndexOf("/", end);
            if (endSlash == apkPathRoot.length() - 1) {
                // Apks cannot be directly under /data/app
                continue;
            }
            int startSlash = potential.lastIndexOf("/", endSlash - 1);
            if (startSlash == -1) {
                continue;
            }
            // Look for the first dash after the package name
            int dash = potential.indexOf("-", startSlash);
            if (dash == -1) {
                continue;
            }
            end = dash;
            String packageName = potential.substring(startSlash + 1, end);
            File dataDir = getWriteableDirectory("/data/data/" + packageName);

            if (dataDir == null) {
                // If we can't access "/data/data", try to guess user specific data directory.
                dataDir = guessUserDataDirectory(packageName);
            }

            if (dataDir != null) {
                File cacheDir = new File(dataDir, "cache");
                // The cache directory might not exist -- create if necessary
                if (fileOrDirExists(cacheDir) || cacheDir.mkdir()) {
                    if (isWriteableDirectory(cacheDir)) {
                        results.add(cacheDir);
                    }
                }
            }
        }
        return results.toArray(new File[results.size()]);
    }

}