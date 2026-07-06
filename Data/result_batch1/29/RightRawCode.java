// https://github.com/VonChange/utao/tree/127583941027a104cf7f39735f95e092a406a6a9/android/x5/app/src/main/java/tv/utao/x5/util/FileUtil.java#L115-L135
public class TempClass {
       }
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }
    public static void unzipFile(String zipPath, String outputDirectory,boolean skipFirst)throws IOException {

}