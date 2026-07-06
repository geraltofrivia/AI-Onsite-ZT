// https://github.com/PBH-BTN/PeerBanHelper/tree/f64e58a4237eda9172a9d03869852bfad512c14d/src/main/java/com/ghostchu/peerbanhelper/util/CommonUtil.java#L28-L50
public class TempClass {
    public static void deleteFileOrDirectory(@NotNull File file) {
        // Traverse the file tree in depth-first fashion and delete each file/directory.
        if(!file.exists()) return;
        if(file.isFile()){
            file.delete();
            return;
        }
        try (var stream = Files.walk(file.toPath())){
                    stream.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            log.warn("Failed to delete file or directory: {}", path, e);
                        }
                    });
        } catch (IOException e) {
            log.warn("Failed to delete file or directory: {}", file, e);
        }
    }

    @NotNull
    public static String getClassPath(@NotNull Class<?> clazz) {

}