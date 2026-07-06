// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-core/src/test/java/org/mockitousage/bugs/creation/PackagePrivateWithContextClassLoaderTest.java#L174-L195
public class TempClass {
        @Override
        public Class<?> findClass(String className) throws ClassNotFoundException {
            try {
                // Create a package for this class, unless it's in the default package.
                int dotpos = className.lastIndexOf('.');
                if (dotpos != -1) {
                    String pkgname = className.substring(0, dotpos);
                    if (getPackage(pkgname) == null) {
                        definePackage(pkgname, null, null, null, null, null, null, null);
                    }
                }
                String resourceName = className.replace('.', File.separatorChar) + ".class";
                InputStream input = getSystemResourceAsStream(resourceName);
                if (input == null) {
                    throw new ClassNotFoundException("Couldn't find resource " + resourceName);
                }
                byte[] classData = loadClassData(input);
                return defineClass(className, classData, 0, classData.length, null);
            } catch (IOException e) {
                throw new ClassNotFoundException("Cannot load " + className, e);
            }
        }

}