// https://github.com/TheAlgorithms/Java/tree/24f4090210fb68b287df9c4ba37b804c6135ef04/src/main/java/com/thealgorithms/datastructures/caches/LIFOCache.java#L118-L142
public class TempClass {
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        lock.lock();
        try {
            evictionStrategy.onAccess(this);

            final CacheEntry<V> entry = cache.get(key);
            if (entry == null || entry.isExpired()) {
                if (entry != null) {
                    cache.remove(key);
                    keys.remove(key);
                    notifyEviction(key, entry.value);
                }
                misses++;
                return null;
            }
            hits++;
            return entry.value;
        } finally {
            lock.unlock();
        }
    }

}