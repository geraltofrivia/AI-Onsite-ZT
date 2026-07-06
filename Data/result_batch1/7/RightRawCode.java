// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/cache/impl/MapDBCachePool.java#L50-L60
public class TempClass {
	@Override
	public Object get(Object key) {
		Object value = htreeMap.get(key);
		if (value != null) {
			cacheStati.incHitTimes();
			return value;
		} else {
			cacheStati.incAccessTimes();
			return null;
		}
	}

}