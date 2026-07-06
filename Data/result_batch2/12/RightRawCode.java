// https://github.com/kabouzeid/Phonograph/tree/d156872cc5ac7f5f295b5fad361974c5fea45631/app/src/main/java/com/kabouzeid/gramophone/model/Song.java#L60-L74
public class TempClass {
    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + trackNumber;
        result = 31 * result + year;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (int) (dateModified ^ (dateModified >>> 32));
        result = 31 * result + (int)albumId;
        result = 31 * result + (albumName != null ? albumName.hashCode() : 0);
        result = 31 * result + (int)artistId;
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        return result;
    }

}