// https://github.com/HotswapProjects/HotswapAgent/tree/1074e9aaed7659654270faae951875cc443379c9/hotswap-agent-core/src/main/java/org/hotswap/agent/javassist/Loader.java#L374-L421
public class TempClass {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classfile;
        try {
            if (source != null) {
                if (translator != null)
                    translator.onLoad(source, name);

                try {
                    classfile = source.get(name).toBytecode();
                }
                catch (NotFoundException e) {
                    return null;
                }
            }
            else {
                String jarname = "/" + name.replace('.', '/') + ".class";
                InputStream in = this.getClass().getResourceAsStream(jarname);
                if (in == null)
                    return null;

                classfile = ClassPoolTail.readStream(in);
            }
        }
        catch (Exception e) {
            throw new ClassNotFoundException(
                "caught an exception while obtaining a class file for "
                + name, e);
        }

        int i = name.lastIndexOf('.');
        if (i != -1) {
            String pname = name.substring(0, i);
            if (isDefinedPackage(pname))
                try {
                    definePackage(
                        pname, null, null, null, null, null, null, null);
                }
                catch (IllegalArgumentException e) {
                    // ignore.  maybe the package object for the same
                    // name has been created just right away.
                }
        }

        if (domain == null)
            return defineClass(name, classfile, 0, classfile.length);
        return defineClass(name, classfile, 0, classfile.length, domain);
    }

}