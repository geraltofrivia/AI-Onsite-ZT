// https://github.com/microsoft/malmo/tree/b59258d812a2600c0c615358684fcf660d83455c/Minecraft/src/main/java/com/microsoft/Malmo/Utils/TextureHelper.java#L353-L384
public class TempClass {
    public static int loadShader(String filename, int shaderType) throws IOException
    {
        int shaderID = -1;
        InputStream stream = MalmoMod.class.getClassLoader().getResourceAsStream(filename);
        if (stream == null)
        {
            System.out.println("Cannot find shader.");
            return -1;
        }
        try
        {
            byte[] abyte = IOUtils.toByteArray((InputStream) (new BufferedInputStream(stream)));
            ByteBuffer bytebuffer = BufferUtils.createByteBuffer(abyte.length);
            bytebuffer.put(abyte);
            bytebuffer.position(0);
            shaderID = OpenGlHelper.glCreateShader(shaderType);
            OpenGlHelper.glShaderSource(shaderID, bytebuffer);
            OpenGlHelper.glCompileShader(shaderID);

            if (OpenGlHelper.glGetShaderi(shaderID, OpenGlHelper.GL_COMPILE_STATUS) == 0)
            {
                String s = StringUtils.trim(OpenGlHelper.glGetShaderInfoLog(shaderID, 32768));
                JsonException jsonexception = new JsonException("Couldn\'t compile shader program: " + s);
                throw jsonexception;
            }
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
        return shaderID;
    }

}