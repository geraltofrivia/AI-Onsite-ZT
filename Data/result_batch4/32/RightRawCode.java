// https://github.com/FudanNLP/fnlp/tree/ce258f3e4a5add2ba0b5e4cbac7cab2190af6659/fnlp-core/src/main/java/org/fnlp/nlp/similarity/train/WordSimilarity.java#L271-L285
public class TempClass {
    public static void main(String[] args) {
        WordSimilarity ws = new WordSimilarity();
        try {
//            ws.dirSougouCAReader("./tmpdata/SogouCa/", "./tmpdata/all.data");
//            ws.countAllC("./tmpdata/all.data");
//            ws.gramString("./tmpdata/all.data");
//            ws.save("./tmpdata/model/");
            ws.read("./tmpdata/model/");
            ws.cluster(100);
            ws.biList2File("./tmpdata/clusterResult");
//            ws.calSimilarity("./tmpdata/statics");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}