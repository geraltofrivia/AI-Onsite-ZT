// https://github.com/find-sec-bugs/find-sec-bugs/tree/e38542b3c810c548fc271a681881557d23e2088e/findsecbugs-plugin/src/main/java/com/h3xstream/findsecbugs/taintanalysis/TaintAnalysis.java#L264-L278
public class TempClass {
    private static List<String> loadFileContent(String path) {
        try (InputStream in = TaintAnalysis.class.getClassLoader().getResourceAsStream(path);
             BufferedReader stream = new BufferedReader(new InputStreamReader(in, "utf-8"))) {
            
            String line;
            List<String> content = new ArrayList<String>();
            while ((line = stream.readLine()) != null) {
                content.add(line.trim());
            }
            return content;
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
        return new ArrayList<String>();
    }

}