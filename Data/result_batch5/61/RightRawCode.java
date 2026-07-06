// https://github.com/SkyTubeTeam/SkyTube/tree/2c717ab729ce5f2fec648c93b04d933e870c71d1/app/src/main/java/free/rm/skytube/businessobjects/YouTube/newpipe/GetPlaylistsForChannel.java#L50-L69
public class TempClass {
        private synchronized List<InfoItem> getNextPage() throws ExtractionException, IOException {
            if (extractor == null) {
                return Collections.emptyList();
            }
            extractor.fetchPage();
            if (firstPage) {
                if (Page.isValid(nextPage)) {
                    ListExtractor.InfoItemsPage<InfoItem> res = extractor.getPage(nextPage);
                    nextPage = res.getNextPage();
                    return res.getItems();
                } else {
                    return Collections.emptyList();
                }
            } else {
                ListExtractor.InfoItemsPage<InfoItem> res = extractor.getInitialPage();
                firstPage = true;
                nextPage = res.getNextPage();
                return res.getItems();
            }
        }

}