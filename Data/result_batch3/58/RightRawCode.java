// https://github.com/xuxueli/xxl-job/tree/685c903aaeec9544339902501402d4a242c5f562/xxl-job-admin/src/main/java/com/xxl/job/admin/core/util/LocalCacheUtil.java#L62-L85
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

    /**
     * remove cache
     *

}