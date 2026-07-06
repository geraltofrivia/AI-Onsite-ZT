// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/backend/jdbc/sequoiadb/SequoiaDriver.java#L37-L88
public class TempClass {
	public Connection connect(String url, Properties info) throws SQLException {
		if (url == null) {
			return null;
		}
		
		if (!StringUtils.startsWithIgnoreCase(url, PREFIX)) {	
			return null;//throw new IllegalArgumentException("uri needs to start with " + PREFIX);//return null;
		}
		String uri=url;
        uri = uri.substring(PREFIX.length());

        String serverPart;
        String nsPart;
        String optionsPart;
        {
            int idx = uri.lastIndexOf("/");
            if (idx < 0) {
                if (uri.contains("?")) {
                    throw new IllegalArgumentException("URI contains options without trailing slash");
                }
                serverPart = uri;
                nsPart = null;
                optionsPart = "";
            } else {
                serverPart = uri.substring(0, idx);
                nsPart = uri.substring(idx + 1);

                idx = nsPart.indexOf("?");
                if (idx >= 0) {
                    optionsPart = nsPart.substring(idx + 1);
                    nsPart = nsPart.substring(0, idx);
                } else {
                    optionsPart = "";
                }

            }
        }		
		SequoiaConnection result = null;
		//System.out.print(info);
		try{
			result = new SequoiaConnection(serverPart, nsPart);
		}catch (Exception e){
			throw new SQLException("Unexpected exception: " + e.getMessage(), e);
		}
		
		return result;
	}
	

	
	@Override
	public boolean acceptsURL(String url) throws SQLException {

}