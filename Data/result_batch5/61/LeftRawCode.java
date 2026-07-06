// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ripper/rippers/ListalRipper.java#L103-L137
public class TempClass {
    @Override
    public Document getNextPage(Document page) throws IOException, URISyntaxException {
        Document nextPage = super.getNextPage(page);
        switch (urlType) {
            case LIST:
                if (!page.select(".loadmoreitems").isEmpty()) {
                    // All items are not loaded.
                    // Load remaining items using postUrl.

                    String offSet = page.select(".loadmoreitems").last().attr("data-offset");
                    Map<String, String> postParams = new HashMap<>();
                    postParams.put("listid", listId);
                    postParams.put("offset", offSet);
                    try {
                        nextPage = Http.url(postUrl).data(postParams).retries(3).post();
                    } catch (IOException e1) {
                        logger.error("Failed to load more images after " + offSet, e1);
                        throw e1;
                    }
                }
                break;

            case FOLDER:
                Elements pageLinks = page.select(".pages a");
                if (!pageLinks.isEmpty() && pageLinks.last().text().startsWith("Next")) {
                    String nextUrl = pageLinks.last().attr("abs:href");
                    nextPage = Http.url(nextUrl).retries(3).get();
                }
                break;

            case UNKNOWN:
            default:
        }
        return nextPage;
    }

}