// https://github.com/WeiYe-Jing/datax-web/tree/f0aac36b6f3c5c6182b8985bd0bcf1470201e92f/datax-admin/src/main/java/com/wugui/datax/admin/core/util/LocalCacheUtil.java#L62-L81
public class TempClass {
    public static boolean set(String key, Object val, long cacheTime){

        // clean timeout cache, before set new cache (avoid cache too much)
        cleanTimeoutCache();

        // set new cache
        if (key==null || key.trim().length()==0) {
            return false;
        }
        if (val == null) {
            remove(key);
        }
        if (cacheTime <= 0) {
            remove(key);
        }
        long timeoutTime = System.currentTimeMillis() + cacheTime;
        LocalCacheData localCacheData = new LocalCacheData(key, val, timeoutTime);
        cacheRepository.put(localCacheData.getKey(), localCacheData);
        return true;
    }

}