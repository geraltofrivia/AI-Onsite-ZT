// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/extension/GeyserExtensionClassLoader.java#L82-L111
public class TempClass {
    protected Class<?> findClass(String name, boolean checkGlobal) throws ClassNotFoundException {
        Class<?> result = this.classes.get(name);
        if (result == null) {
            // Try to find class in current extension
            try {
                result = super.findClass(name);
            } catch (ClassNotFoundException ignored) {
                // If class is not found in current extension, check in the global class loader
                // This is used for classes that are not in the extension, but are in other extensions
                if (checkGlobal) {
                    if (!warnedForExternalClassAccess) {
                        GeyserImpl.getInstance().getLogger().warning("Extension " + this.description.name() + " loads class " + name + " from an external source. " +
                                "This can change at any time and break the extension, additionally to potentially causing unexpected behaviour!");
                        warnedForExternalClassAccess = true;
                    }
                    result = this.loader.classByName(name);
                }
            }

            if (result != null) {
                // If class is found, cache it
                this.loader.setClass(name, result);
                this.classes.put(name, result);
            } else {
                // If class is not found, throw exception
                throw new ClassNotFoundException(name);
            }
        }
        return result;
    }

}