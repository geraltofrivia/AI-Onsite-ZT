// https://github.com/coobird/thumbnailator/tree/951d04f352d90f34420a82909c15453fee1b75ff/src/test/java/net/coobird/thumbnailator/TestUtils.java#L96-L111
public class TempClass {
	public static File copyResourceToFile(String resourceName, File destination) throws IOException {
		InputStream is = getResourceStream(resourceName);
		FileOutputStream fos = new FileOutputStream(destination);

		byte[] buffer;
		int bytesAvailable;
		while ((bytesAvailable = is.available()) != 0) {
			buffer = new byte[bytesAvailable];
			int bytesRead = is.read(buffer, 0, buffer.length);
			fos.write(buffer, 0, bytesRead);
		}
		is.close();
		fos.close();

		return destination;
	}

}