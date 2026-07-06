// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/FiltersPlugin/src/main/java/org/gephi/filters/plugin/partition/PartitionBuilder.java#L266-L275
public class TempClass {
        private boolean listContains(Object value) {
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                Object val = Array.get(value, i);
                if (parts.contains(val)) {
                    return true;
                }
            }
            return false;
        }

}