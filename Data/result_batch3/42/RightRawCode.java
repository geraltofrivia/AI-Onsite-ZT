// https://github.com/trojan-gfw/igniter/tree/e0964bc5f4cd8c0c481405a1a38e9e493791c002/app/src/main/java/io/github/trojan_gfw/igniter/servers/data/ServerListDataManager.java#L206-L219
public class TempClass {
    private String readStringFromStream(InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            char[] buf = new char[4096];
            StringBuilder sb = new StringBuilder();
            int readSize;
            while ((readSize = reader.read(buf)) != -1) {
                sb.append(buf, 0, readSize);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}