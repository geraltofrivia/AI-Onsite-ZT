// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/main/java/moe/loliserver/Metrics.java#L143-L152
public class TempClass {
        private JSONObject getRequestJsonObject() {
            JSONObject chart = new JSONObject();
            chart.put("chartId", chartId);
            try {
                JSONObject data = getChartData();
                if (data == null) return null;
                chart.put("data", data);
            } catch (Throwable t) { return null; }
            return chart;
        }

}