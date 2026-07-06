// https://github.com/nining377/dolby_beta/tree/258ab9b95671ecfb13a03636842890d9ca36fe75/app/src/main/java/com/raincat/dolby_beta/helper/FileHelper.java#L88-L106
public class TempClass {
    static List<String> readFileFromSD(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        if (!file.isDirectory()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    list.add(line);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}