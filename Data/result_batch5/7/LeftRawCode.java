// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-plugin/server-plugin-sso-discord/src/main/java/io/onedev/server/plugin/sso/discord/DiscordAuthorizationCodeResponse.java#L45-L82
public class TempClass {
	private static Map<String,String> parseParameters(final String query) {
		
		Map<String,String> params = new HashMap<>();
		
		if (query == null || query.trim().isEmpty()) {
			return params; // empty map
		}
		
		try {
			StringTokenizer st = new StringTokenizer(query.trim(), "&");

			while(st.hasMoreTokens()) {

				String param = st.nextToken();

				String pair[] = param.split("=");

				String key = URLDecoder.decode(pair[0], CHARSET);
				
				// Save the first value only
				if (params.containsKey(key))
					continue;

				String value = "";

				if (pair.length > 1)
					value = URLDecoder.decode(pair[1], CHARSET);
				
				params.put(key, value);
			}
			
		} catch (UnsupportedEncodingException e) {
			
			// UTF-8 should always be supported
		}
		
		return params;
	}

}