// https://github.com/oshi/oshi/tree/35badc331ff64b262629f552d8b81034a08ab432/oshi-core/src/main/java/oshi/util/ParseUtil.java#L1055-L1076
public class TempClass {
    public static List<Integer> parseHyphenatedIntList(String str) {
        List<Integer> result = new ArrayList<>();
        String[] csvTokens = str.split(",");
        for (String csvToken : csvTokens) {
            csvToken = csvToken.trim();
            for (String s : whitespaces.split(csvToken)) {
                if (s.contains("-")) {
                    int first = getFirstIntValue(s);
                    int last = getNthIntValue(s, 2);
                    for (int i = first; i <= last; i++) {
                        result.add(i);
                    }
                } else {
                    int only = ParseUtil.parseIntOrDefault(s, -1);
                    if (only >= 0) {
                        result.add(only);
                    }
                }
            }
        }
        return result;
    }

}