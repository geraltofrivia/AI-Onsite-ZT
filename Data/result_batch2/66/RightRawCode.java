// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane/src/main/java/org/freeplane/main/application/ApplicationResourceController.java#L194-L242
public class TempClass {
	@Override
	public URL getResource(final String resourcePath) {
		return AccessController.doPrivileged(new PrivilegedAction<URL>() {

			@Override
			public URL run() {
				final String relName = removeSlashAtStart(resourcePath);
				for(File directory : resourceDirectories) {
					File fileResource = new File(directory, relName);
					if (fileResource.exists()) {
						try {
							return Compat.fileToUrl(fileResource);
						} catch (MalformedURLException e) {
							throw new RuntimeException(e);
						}
					}
				}
				{
				    URL resource = ApplicationResourceController.super.getResource(resourcePath);
				    if (resource != null) {
				        return resource;
				    }
				}
				if ("/lib/freeplaneviewer.jar".equals(resourcePath)) {
				    final String rootDir = new File(getResourceBaseDir()).getAbsoluteFile().getParent();
					try {
						final File try1 = new File(rootDir + "/plugins/org.freeplane.core/lib/freeplaneviewer.jar");
						if (try1.exists()) {
							return try1.toURL();
						}
						final File try2 = new File(rootDir + "/lib/freeplaneviewer.jar");
						if (try2.exists()) {
							return try2.toURL();
						}
					}
					catch (final MalformedURLException e) {
						e.printStackTrace();
					}
				}
				for(ClassLoader loader : resourceLoaders) {
				    URL resource = loader.getResource(resourcePath);
				    if(resource  != null)
				        return resource;
				}

				return null;
			}
		});
	}

}