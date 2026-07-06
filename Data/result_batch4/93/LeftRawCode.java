// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/util/Metrics.java#L384-L412
public class TempClass {
        @Override
        public @Nullable ObjectNode getChartData() throws Exception {
            ObjectNode data = mapper.createObjectNode();
            ObjectNode values = mapper.createObjectNode();
            Map<String, Map<String, Integer>> map = callable.call();
            if (map == null || map.isEmpty()) {
                // Null = skip the chart
                return null;
            }
            boolean reallyAllSkipped = true;
            for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
                ObjectNode value = mapper.createObjectNode();
                boolean allSkipped = true;
                for (Map.Entry<String, Integer> valueEntry : map.get(entryValues.getKey()).entrySet()) {
                    value.put(valueEntry.getKey(), valueEntry.getValue());
                    allSkipped = false;
                }
                if (!allSkipped) {
                    reallyAllSkipped = false;
                    values.putPOJO(entryValues.getKey(), value);
                }
            }
            if (reallyAllSkipped) {
                // Null = skip the chart
                return null;
            }
            data.putPOJO("values", values);
            return data;
        }

}