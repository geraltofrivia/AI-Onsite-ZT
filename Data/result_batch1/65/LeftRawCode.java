// https://github.com/dreamhead/moco/tree/10f44fdb2d465ce27951e7b012e1ae59c8e13d89/moco-runner/src/main/java/com/github/dreamhead/moco/runner/watcher/WatcherService.java#L137-L156
public class TempClass {
    public void unregister(final File file) {
        Path directory = Files.directoryOf(file).toPath();
        Path path = file.toPath();
        if (!directoryToFiles.containsEntry(directory, path)) {
            return;
        }

        directoryToFiles.remove(directory, path);

        if (!directoryToFiles.containsKey(directory)) {
            WatchKey key = directoryToKey.remove(directory);
            if (key != null) {
                key.cancel();
            }
        }

        if (directoryToFiles.isEmpty()) {
            this.stop();
        }
    }

}