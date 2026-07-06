// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/core/model/stream/TorrentInputStream.java#L357-L384
public class TempClass {
    @Override
    public long skip(long n) {
        lock.lock();

        try {
            if (n <= 0)
                return 0;

            /* EOF check */
            if (filePos == eof)
                return 0;
            if (filePos + n > eof)
                n = (int) (eof - filePos);

            filePos += n;

            if (session != null) {
                TorrentDownload task = session.getTask(stream.torrentId);
                if (task != null)
                    task.setInterestedPieces(stream, stream.bytesToPieceIndex(filePos + 1), 1);
            }

            return n;

        } finally {
            lock.unlock();
        }
    }

}