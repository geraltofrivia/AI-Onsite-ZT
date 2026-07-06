// https://github.com/android-hacker/VirtualXposed/tree/122beb371519cb2d221ce06756361aaa30e2674f/VirtualApp/lib/src/main/java/com/lody/virtual/helper/collection/SimpleArrayMap.java#L428-L442
public class TempClass {
    public void putAll(SimpleArrayMap<? extends K, ? extends V> array) {
        final int N = array.mSize;
        ensureCapacity(mSize + N);
        if (mSize == 0) {
            if (N > 0) {
                System.arraycopy(array.mHashes, 0, mHashes, 0, N);
                System.arraycopy(array.mArray, 0, mArray, 0, N<<1);
                mSize = N;
            }
        } else {
            for (int i=0; i<N; i++) {
                put(array.keyAt(i), array.valueAt(i));
            }
        }
    }

}