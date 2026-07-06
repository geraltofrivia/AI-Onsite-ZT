// https://github.com/Etar-Group/Etar-Calendar/tree/b428755a98ddd03e7ed87d3645faaa2486e03bf4/app/src/main/java/com/android/calendar/calendarcommon2/EventRecurrence.java#L702-L719
public class TempClass {
        public static int[] parseNumberList(String listStr, int minVal, int maxVal,
                boolean allowZero) {
            int[] values;

            if (listStr.indexOf(",") < 0) {
                // Common case: only one entry, skip split() overhead.
                values = new int[1];
                values[0] = parseIntRange(listStr, minVal, maxVal, allowZero);
            } else {
                String[] valueStrs = listStr.split(",");
                int len = valueStrs.length;
                values = new int[len];
                for (int i = 0; i < len; i++) {
                    values[i] = parseIntRange(valueStrs[i], minVal, maxVal, allowZero);
                }
            }
            return values;
        }

}