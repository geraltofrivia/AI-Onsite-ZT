// https://github.com/lets-mica/mica/tree/b550c707a7d623258b3249a7ee8db8b0530861ff/mica-http/src/main/java/net/dreamlu/mica/http/HttpRequest.java#L688-L709
public class TempClass {
																   String trustStoreFile, String trustPass) {
		InputStream keyStoreInputStream;
		if (keyStoreFile == null) {
			keyStoreInputStream = null;
		} else if (keyStoreFile.toLowerCase().startsWith("classpath:")) {
			keyStoreInputStream = getResourceAsStream(keyStoreFile);
		} else {
			keyStoreInputStream = getFileResource(keyStoreFile);
		}
		InputStream trustStoreInputStream;
		if (trustStoreFile == null) {
			trustStoreInputStream = null;
		} else if (trustStoreFile.toLowerCase().startsWith("classpath:")) {
			trustStoreInputStream = getResourceAsStream(trustStoreFile);
		} else {
			trustStoreInputStream = getFileResource(trustStoreFile);
		}
		return getSslContext(keyStoreInputStream, keyPass, trustStoreInputStream, trustPass);
	}

	public static Pair<SSLContext, X509TrustManager> getSslContext(InputStream keyStoreInputStream, String keyPass,
																   InputStream trustInputStream, String trustPass) {

}