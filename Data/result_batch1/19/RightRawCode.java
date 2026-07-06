// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/main/java/moe/loliserver/Metrics.java#L102-L114
public class TempClass {
    public JSONObject getPluginData() {
        JSONObject data = new JSONObject();

        data.put("pluginName", name); // Append the name of the server software
        JSONArray customCharts = new JSONArray();
        for (CustomChart customChart : charts) {
            JSONObject chart = customChart.getRequestJsonObject();
            if (chart == null) continue;
            customCharts.add(chart);
        }
        data.put("customCharts", customCharts);
        return data;
    }

}