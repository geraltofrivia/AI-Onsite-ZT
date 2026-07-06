// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/util/Metrics.java#L130-L148
public class TempClass {
    private ObjectNode getPluginData() {
        ObjectNode data = mapper.createObjectNode();

        data.put("pluginName", name); // Append the name of the server software
        data.put("pluginVersion", GeyserImpl.VERSION); // Append the name of the server software

        ArrayNode customCharts = mapper.createArrayNode();
        for (CustomChart customChart : charts) {
            // Add the data of the custom charts
            JsonNode chart = customChart.getRequestJsonNode();
            if (chart == null) { // If the chart is null, we skip it
                continue;
            }
            customCharts.add(chart);
        }
        data.set("customCharts", customCharts);

        return data;
    }

}