// https://github.com/FudanNLP/fnlp/tree/ce258f3e4a5add2ba0b5e4cbac7cab2190af6659/fnlp-core/src/main/java/org/fnlp/nlp/corpus/WordCount.java#L46-L56
public class TempClass {
	public static void main(String[] args) throws Exception {
		
		WordCount wc = new WordCount();
		 wc.seg = new CWSTagger("../models/seg.m");
		
		wc.count("./tmp/filterTweets.y");
		wc.count("./tmp/filterTweets.n");		
		wc.write("./tmp/wc.txt", true);
		wc.filter(500);
		wc.write("./tmp/wcc.txt", false);
	}

}