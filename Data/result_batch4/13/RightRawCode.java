// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/com/ernieyu/feedparser/EzRssTorrentItem.java#L97-L113
public class TempClass {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EzRssTorrentItem that = (EzRssTorrentItem) o;

        if (contentLength != that.contentLength) return false;
        if (seeds != that.seeds) return false;
        if (peers != that.peers) return false;
        if (verified != that.verified) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null)
            return false;
        if (magnetUri != null ? !magnetUri.equals(that.magnetUri) : that.magnetUri != null)
            return false;
        return infoHash != null ? infoHash.equals(that.infoHash) : that.infoHash == null;
    }

}