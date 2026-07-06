// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-core/src/main/java/software/coley/recaf/services/plugin/PluginClassLoaderImpl.java#L93-L109
public class TempClass {
	@Nullable
	Class<?> lookupClassImpl(@Nonnull String name) throws ClassNotFoundException {
		Class<?> cls = findLoadedClass(name);
		if (cls != null)
			return cls;
		ByteSource classBytes = source.findResource(name.replace('.', '/') + ".class");
		if (classBytes != null) {
			byte[] bytes;
			try {
				bytes = classBytes.readAll();
			} catch (IOException ex) {
				throw new ClassNotFoundException(name, ex);
			}
			return defineClass(name, bytes, 0, bytes.length);
		}
		return null;
	}

}