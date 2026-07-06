// https://github.com/Neamar/KISS/tree/eed0e45f7ad9dd488858f7daaa1e079dc700b792/app/src/main/java/fr/neamar/kiss/utils/IconPackCache.java#L58-L72
public class TempClass {
        @Nullable
        public V get(K key) {
            V value = null;

            SoftReference<V> reference = mCache.get(key);

            if (reference != null) {
                value = reference.get();
            }

            if (value == null)
                mCache.remove(key);

            return value;
        }

}