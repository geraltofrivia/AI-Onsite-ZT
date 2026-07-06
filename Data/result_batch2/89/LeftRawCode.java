// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/job/log/DefaultLogManager.java#L281-L300
public class TempClass {
	private List<JobLogEntryEx> readLogEntries(File logFile, int from, int count) {
		List<JobLogEntryEx> entries = new ArrayList<>();
		if (logFile.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(logFile)))) {
				int numOfReadEntries = 0;
				while (numOfReadEntries < from) {
					ois.readObject();
					numOfReadEntries++;
				}
				while (count == 0 || numOfReadEntries - from < count) {
					entries.add(readLogEntry(ois));
					numOfReadEntries++;
				}
			} catch (EOFException ignored) {
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return entries;
	}

}