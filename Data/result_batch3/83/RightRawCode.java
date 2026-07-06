// https://github.com/proninyaroslav/libretorrent/tree/92fdf65ec5c0e9358c0e77ae107097a77bf92189/app/src/main/java/org/proninyaroslav/libretorrent/core/collections/FixedRingBuffer.java#L112-L123
public class TempClass {
    public int indexOf(E element)
    {
        if (element == null)
            return -1;

        for (int i = 0; i < size(); i++) {
            if (element.equals(elements[(head + i) % capacity]))
                return i;
        }

        return -1;
    }

}