// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-extensions/mockito-proxy/src/test/java/org/mockitoproxy/MocksTest.java#L78-L99
public class TempClass {
                    @Override
                    public Class<?> findClass(String name) throws ClassNotFoundException {
                        if (name.startsWith(MocksTest.class.getPackage().getName())) {
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            try (InputStream inputStream =
                                    MocksTest.class
                                            .getClassLoader()
                                            .getResourceAsStream(
                                                    name.replace('.', '/') + ".class")) {
                                int length;
                                byte[] buffer = new byte[1024];
                                while ((length = inputStream.read(buffer)) != -1) {
                                    outputStream.write(buffer, 0, length);
                                }
                            } catch (IOException e) {
                                throw new AssertionError(e);
                            }
                            byte[] classFile = outputStream.toByteArray();
                            return defineClass(name, classFile, 0, classFile.length);
                        }
                        return super.loadClass(name);
                    }

}