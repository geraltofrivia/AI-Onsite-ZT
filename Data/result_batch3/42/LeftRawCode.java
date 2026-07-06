// https://github.com/abel533/Mapper/tree/7990b9c48bbcebf590f09fc245daba0c396f7b57/generator/src/main/java/tk/mybatis/mapper/generator/TemplateFilePlugin.java#L128-L140
public class TempClass {
    protected String read(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));
        StringBuffer stringBuffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            stringBuffer.append(line);
            line = reader.readLine();
            if (line != null) {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

}