// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ui/MainWindow.java#L1270-L1304
public class TempClass {
    private void loadHistory() throws IOException {
        File historyFile = new File(Utils.getConfigDir() + "/history.json");
        HISTORY.clear();
        if (historyFile.exists()) {
            try {
                LOGGER.info(Utils.getLocalizedString("loading.history.from") + " " + historyFile.getCanonicalPath());
                HISTORY.fromFile(historyFile.getCanonicalPath());
            } catch (IOException e) {
                LOGGER.error("Failed to load history from file " + historyFile, e);
                JOptionPane.showMessageDialog(null,
                        String.format(Utils.getLocalizedString("history.load.failed.warning"), e.getMessage()),

                        "RipMe - history load failure", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            LOGGER.info(Utils.getLocalizedString("loading.history.from.configuration"));
            HISTORY.fromList(Utils.getConfigList("download.history"));
            if (HISTORY.toList().isEmpty()) {
                // Loaded from config, still no entries.
                // Guess rip history based on rip folder
                Stream<Path> stream = Files.list(Utils.getWorkingDirectory())
                        .filter(Files::isDirectory);

                stream.forEach(dir -> {
                    String url = RipUtils.urlFromDirectoryName(dir.toString());
                    if (url != null) {
                        // We found one, add it to history
                        HistoryEntry entry = new HistoryEntry();
                        entry.url = url;
                        HISTORY.add(entry);
                    }
                });
            }
        }
    }

}