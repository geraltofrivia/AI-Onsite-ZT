// https://github.com/ximsfei/Android-skin-support/tree/3eaa81f5999342018186fb28a2d915fc8605e4c4/androidx/skin-support/src/main/java/skin/support/collection/SimpleArrayMap.java#L136-L170
public class TempClass {
    @SuppressWarnings("ArrayToString")
    private void allocArrays(final int size) {
        if (size == (BASE_SIZE * 2)) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCache != null) {
                    final Object[] array = mTwiceBaseCache;
                    mArray = array;
                    mTwiceBaseCache = (Object[]) array[0];
                    mHashes = (int[]) array[1];
                    array[0] = array[1] = null;
                    mTwiceBaseCacheSize--;
                    if (DEBUG) System.out.println(TAG + " Retrieving 2x cache " + mHashes
                            + " now have " + mTwiceBaseCacheSize + " entries");
                    return;
                }
            }
        } else if (size == BASE_SIZE) {
            synchronized (ArrayMap.class) {
                if (mBaseCache != null) {
                    final Object[] array = mBaseCache;
                    mArray = array;
                    mBaseCache = (Object[]) array[0];
                    mHashes = (int[]) array[1];
                    array[0] = array[1] = null;
                    mBaseCacheSize--;
                    if (DEBUG) System.out.println(TAG + " Retrieving 1x cache " + mHashes
                            + " now have " + mBaseCacheSize + " entries");
                    return;
                }
            }
        }

        mHashes = new int[size];
        mArray = new Object[size << 1];
    }

}