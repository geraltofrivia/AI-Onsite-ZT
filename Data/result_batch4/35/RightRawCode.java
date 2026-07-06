// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/core/util/FileUtils.java#L28-L44
public class TempClass {
	public static void copyFromURL(final URL resource, final File destinationDirectory) {
		final String path = resource.getPath();
		final int index = path.lastIndexOf('/');
		try {
            String fileName = index > -1 ? path.substring(index + 1) : path;
            if("file".equals(resource.getProtocol()))
                fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
            try (InputStream in = resource.openStream();
                OutputStream out = new FileOutputStream(new File(destinationDirectory, fileName))) {
                    FileUtils.copyStream(in, out);
            }
		}
		catch (final Exception e) {
		    LogUtils.severe("File not found or could not be copied. " + "Was searching for " + path
		            + " and should go to " + destinationDirectory.getAbsolutePath());
		}
	}

}