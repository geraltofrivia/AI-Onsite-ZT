// https://github.com/aporter/coursera-android/tree/157373885fbfa18b83fa97cd46f6a003905970ea/Examples/NetworkingURL/src/course/examples/networking/url/NetworkingURLActivity.java#L80-L101
public class TempClass {
		private String readStream(InputStream in) {
			BufferedReader reader = null;
			StringBuffer data = new StringBuffer("");
			try {
				reader = new BufferedReader(new InputStreamReader(in));
				String line = "";
				while ((line = reader.readLine()) != null) {
					data.append(line);
				}
			} catch (IOException e) {
				Log.e(TAG, "IOException");
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return data.toString();
		}

}