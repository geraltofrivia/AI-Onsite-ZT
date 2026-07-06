// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/core/model/data/TorrentInfo.java#L196-L220
public class TempClass {
    @Override
    public int hashCode() {
        int prime = 31, result = 1;

        result = prime * result + torrentId.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + progress;
        result = prime * result + Long.hashCode(receivedBytes);
        result = prime * result + Long.hashCode(uploadedBytes);
        result = prime * result + Long.hashCode(totalBytes);
        result = prime * result + Long.hashCode(downloadSpeed);
        result = prime * result + Long.hashCode(uploadSpeed);
        result = prime * result + Long.hashCode(ETA);
        result = prime * result + Long.hashCode(dateAdded);
        result = prime * result + totalPeers;
        result = prime * result + peers;
        result = prime * result + ((error == null) ? 0 : error.hashCode());
        result = prime * result + (sequentialDownload ? 1 : 0);
        result = prime * result + Arrays.hashCode(filePriorities);
        result = prime * result + tags.hashCode();
        result = prime * result + (firstLastPiecePriority ? 1 : 0);

        return result;
    }

}