// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/core/urlnormalizer/NormalizeUrl.java#L333-L362
public class TempClass {
    private static Map<String, Collection<String>> parseQuery(String query)
    {
        HashMap<String, Collection<String>> queryPairs = new HashMap<>();

        if (query == null || query.isEmpty() || query.equals("?"))
            return queryPairs;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            /* Ignore nested query string in the value of the pair, if one exists */
            int equalPos = pair.indexOf('=');
            if (equalPos <= 0)
                continue;

            String key = pair.substring(0, equalPos);
            String value = pair.substring(equalPos + 1);

            if (!key.isEmpty()) {
                Collection<String> existing = queryPairs.get(key);
                if (existing == null)
                    existing = new ArrayList<>();

                if (!value.isEmpty())
                    existing.add(value);
                queryPairs.put(key, existing);
            }
        }

        return queryPairs;
    }

}