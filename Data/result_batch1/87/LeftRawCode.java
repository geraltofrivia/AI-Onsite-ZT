// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ripper/rippers/MangadexRipper.java#L94-L149
public class TempClass {
    @Override
    protected List<String> getURLsFromJSON(JSONObject json) {
        if (isSingleChapter) {
            List<String> assetURLs = new ArrayList<>();
            JSONArray currentObject;
            String chapterHash;
            // Server is the cdn hosting the images.
            String server;
            chapterHash = json.getString("hash");
            server = json.getString("server");
            for (int i = 0; i < json.getJSONArray("page_array").length(); i++) {
                currentObject = json.getJSONArray("page_array");

                assetURLs.add(getImageUrl(chapterHash, currentObject.getString(i), server));
            }
            return assetURLs;
        }
        JSONObject chaptersJSON = (JSONObject) json.get("chapter");
        JSONObject temp;
        Iterator<String> keys = chaptersJSON.keys();
        HashMap<Double, String> chapterIDs = new HashMap<>();
        while (keys.hasNext()) {
            String keyValue = keys.next();
            temp = (JSONObject) chaptersJSON.get(keyValue);
            if (temp.getString("lang_name").equals("English")) {
                chapterIDs.put(temp.getDouble("chapter"), keyValue);
            }

        }

        List<String> assetURLs = new ArrayList<>();
        JSONArray currentObject;
        String chapterHash;
        // Server is the cdn hosting the images.
        String server;
        JSONObject chapterJSON = null;
        TreeMap<Double, String> treeMap = new TreeMap<>(chapterIDs);
        for (Double aDouble : treeMap.keySet()) {
            double key = (double) aDouble;
            try {
                chapterJSON = Http.url(new URI(chapterApiEndPoint + treeMap.get(key)).toURL()).getJSON();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            sendUpdate(RipStatusMessage.STATUS.LOADING_RESOURCE, "chapter " + key);
            chapterHash = chapterJSON.getString("hash");
            server = chapterJSON.getString("server");
            for (int i = 0; i < chapterJSON.getJSONArray("page_array").length(); i++) {
                currentObject = chapterJSON.getJSONArray("page_array");

                assetURLs.add(getImageUrl(chapterHash, currentObject.getString(i), server));
            }
        }

        return assetURLs;
    }

}