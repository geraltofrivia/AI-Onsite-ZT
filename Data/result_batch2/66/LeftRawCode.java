// https://github.com/testcontainers/testcontainers-java/tree/768a2af266bf20be4800281053c83d4d1f345942/core/src/main/java/org/testcontainers/utility/MountableFile.java#L128-L164
public class TempClass {
    @NotNull
    private static URL getClasspathResource(
        @NotNull final String resourcePath,
        @NotNull final Set<ClassLoader> classLoaders
    ) {
        final Set<ClassLoader> classLoadersToSearch = new HashSet<>(classLoaders);
        // try context and system classloaders as well
        classLoadersToSearch.add(Thread.currentThread().getContextClassLoader());
        classLoadersToSearch.add(ClassLoader.getSystemClassLoader());
        classLoadersToSearch.add(MountableFile.class.getClassLoader());

        for (final ClassLoader classLoader : classLoadersToSearch) {
            if (classLoader == null) {
                continue;
            }

            URL resource = classLoader.getResource(resourcePath);
            if (resource != null) {
                return resource;
            }

            // Be lenient if an absolute path was given
            if (resourcePath.startsWith("/")) {
                resource = classLoader.getResource(resourcePath.replaceFirst("/", ""));
                if (resource != null) {
                    return resource;
                }
            }
        }

        throw new IllegalArgumentException(
            "Resource with path " +
            resourcePath +
            " could not be found on any of these classloaders: " +
            classLoadersToSearch
        );
    }

}