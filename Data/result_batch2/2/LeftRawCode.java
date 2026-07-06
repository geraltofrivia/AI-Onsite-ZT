// https://github.com/frohoff/ysoserial/tree/b7d0f27b46af06bbced7dbafddc49678179d3708/src/main/java/ysoserial/payloads/util/ClassFiles.java#L25-L42
public class TempClass {
	public static byte[] classAsBytes(final Class<?> clazz) {
		try {
			final byte[] buffer = new byte[1024];
			final String file = classAsFile(clazz);
			final InputStream in = ClassFiles.class.getClassLoader().getResourceAsStream(file);
			if (in == null) {
				throw new IOException("couldn't find '" + file + "'");
			}
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}