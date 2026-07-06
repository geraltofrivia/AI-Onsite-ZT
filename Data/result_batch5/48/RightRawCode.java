// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/config/util/JVMInfo.java#L140-L151
public class TempClass {
    public Class<?> loadClass(String name) {
        try {
            Class<?> clazz = loaderCache.get(name);
            if (clazz == null) {
                clazz = Class.forName(name, false, getClass().getClassLoader());
                loaderCache.put(name, clazz);
            }
            return clazz;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}