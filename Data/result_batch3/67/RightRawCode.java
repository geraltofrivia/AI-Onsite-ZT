// https://github.com/jeequan/jeepay/tree/f53cb2132934823389a8c9de8a4a049f7ab1cfb0/jeepay-payment/src/main/java/com/jeequan/jeepay/pay/channel/ysfpay/utils/YsfHttpUtil.java#L242-L258
public class TempClass {
		try {
			reader = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static String buildGetUrl(String url, Map<String, Object> params) throws Exception {
		String queryParams = buildQueryParams(params);

}