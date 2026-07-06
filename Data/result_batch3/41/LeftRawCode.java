// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-core/src/main/java/software/coley/recaf/services/compile/VirtualFileManager.java#L37-L58
public class TempClass {
	@Override
	public Iterable<JavaFileObject> list(@Nonnull Location location, @Nonnull String packageName,
										 @Nonnull Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
		Iterable<JavaFileObject> list = super.list(location, packageName, kinds, recurse);
		if (StandardLocation.CLASS_PATH.equals(location) && kinds.contains(JavaFileObject.Kind.CLASS)) {
			String formatted = packageName.isEmpty() ? "" : packageName.replace('.', '/') + '/';
			Predicate<String> check;
			if (recurse) {
				check = name -> name.startsWith(formatted);
			} else {
				check = name -> name.startsWith(formatted) &&
						name.indexOf('/', formatted.length()) == -1;
			}
			return () -> new ClassPathIterator(list.iterator(), virtualClasspath.stream()
					.flatMap(resource -> resource.getJvmClassBundle().entrySet().stream())
					.filter(entry -> check.test(entry.getKey()))
					.<JavaFileObject>map(entry -> new ResourceVirtualJavaFileObject(entry.getKey(),
							entry.getValue().getBytecode(), JavaFileObject.Kind.CLASS))
					.iterator());
		}
		return list;
	}

}