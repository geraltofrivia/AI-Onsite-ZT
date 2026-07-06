// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ripper/rippers/ArtStationRipper.java#L313-L330
public class TempClass {
    private JSONObject getJson(URL url) throws IOException {
        Connection con = Http.url(url).method(Method.GET).connection();
        con.ignoreHttpErrors(true);
        con.ignoreContentType(true);
        con.userAgent(
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        con.header("Accept-Language", "en-US,en;q=0.5");
//        con.header("Accept-Encoding", "gzip, deflate, br");
        con.header("Upgrade-Insecure-Requests", "1");
        Response res = con.execute();
        int status = res.statusCode();
        if (status / 100 == 2) {
            String jsonString = res.body();
            return new JSONObject(jsonString);
        }
        throw new IOException("Error fetching json. Status code:" + status);
    }

}