// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/PluginFirstClassLoader2.java#L58-L77
public class TempClass {
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                try {
                    c = findClass(name);
                } catch (ClassNotFoundException e) {
                    // ignore
                }
            }
            if (c == null) {
                c = getParent().loadClass(name);
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

}