// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/core/model/data/PeerInfo.java#L172-L188
public class TempClass {
    @Override
    public int hashCode() {
        int prime = 31, result = 1;

        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + Long.hashCode(totalDownload);
        result = prime * result + Long.hashCode(totalUpload);
        result = prime * result + Double.hashCode(relevance);
        result = prime * result + connectionType;
        result = prime * result + port;
        result = prime * result + progress;
        result = prime * result + downSpeed;
        result = prime * result + upSpeed;

        return result;
    }

}