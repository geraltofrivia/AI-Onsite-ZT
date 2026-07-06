// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/util/Metrics.java#L266-L283
public class TempClass {
        private @Nullable ObjectNode getRequestJsonNode() {
            ObjectNode chart = new ObjectMapper().createObjectNode();
            chart.put("chartId", chartId);
            try {
                ObjectNode data = getChartData();
                if (data == null) {
                    // If the data is null we don't send the chart.
                    return null;
                }
                chart.putPOJO("data", data);
            } catch (Throwable t) {
                if (logFailedRequests) {
                    logger.log(Level.WARNING, "Failed to get data for custom chart with id " + chartId, t);
                }
                return null;
            }
            return chart;
        }

}