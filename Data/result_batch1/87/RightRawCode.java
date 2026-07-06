// https://github.com/Haleydu/Cimoc/tree/891eb3f7ce51a940fd144795798c03b8e2058c0f/app/src/main/java/com/haleydu/cimoc/source/HotManga.java#L194-L212
public class TempClass {
    public List<ImageUrl> parseImages(String html, Chapter chapter) throws JSONException {
        List<ImageUrl> list = new LinkedList<>();
        JSONObject jsonObject = new JSONObject(html);
        JSONArray array = jsonObject.getJSONObject("results").getJSONObject("chapter").getJSONArray("contents");
        try {
            for (int i = 0; i < array.length(); ++i) {
                Long comicChapter = chapter.getId();
                Long id = Long.parseLong(comicChapter + "000" + i);
                String url = array.getJSONObject(i).getString("url").replace("m_read","kb_m_read_large");
                list.add(new ImageUrl(id, comicChapter,i + 1, url, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Request getCheckRequest(String cid) {

}