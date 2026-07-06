// https://github.com/Konloch/bytecode-viewer/tree/903ac6a3fd19f1a3047789a91fb35e48d60459fb/src/main/java/the/bytecode/club/bytecodeviewer/util/ClassFileUtils.java#L35-L46
public class TempClass {
    public static byte[] getClassFileBytes(Class<?> clazz) throws IOException
    {
        try (InputStream is = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + ".class");
             ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            int r;
            byte[] buffer = new byte[8192];
            while ((r = Objects.requireNonNull(is).read(buffer)) >= 0)
                baos.write(buffer, 0, r);
            return baos.toByteArray();
        }
    }

}