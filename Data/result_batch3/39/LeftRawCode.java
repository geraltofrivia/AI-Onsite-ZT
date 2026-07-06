// https://github.com/ximsfei/Android-skin-support/tree/3eaa81f5999342018186fb28a2d915fc8605e4c4/androidx/skin-support/src/main/java/skin/support/collection/SimpleArrayMap.java#L457-L471
public class TempClass {
    public void putAll(SimpleArrayMap<? extends K, ? extends V> array) {
        final int N = array.mSize;
        ensureCapacity(mSize + N);
        if (mSize == 0) {
            if (N > 0) {
                System.arraycopy(array.mHashes, 0, mHashes, 0, N);
                System.arraycopy(array.mArray, 0, mArray, 0, N << 1);
                mSize = N;
            }
        } else {
            for (int i = 0; i < N; i++) {
                put(array.keyAt(i), array.valueAt(i));
            }
        }
    }

}