// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/main/java/net/minecraftforge/fml/common/ObfuscationReflectionHelper.java#L184-L210
public class TempClass {
    @Nonnull
    public static <T> Constructor<T> findConstructor(@Nonnull final Class<T> clazz, @Nonnull final Class<?>... parameterTypes)
    {
        Preconditions.checkNotNull(clazz, "Class to find constructor on cannot be null.");
        Preconditions.checkNotNull(parameterTypes, "Parameter types of constructor to find cannot be null.");

        try
        {
            Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor;
        }
        catch (final NoSuchMethodException e)
        {
            final StringBuilder desc = new StringBuilder();
            desc.append(clazz.getSimpleName());

            StringJoiner joiner = new StringJoiner(", ", "(", ")");
            for (Class<?> type : parameterTypes)
            {
                joiner.add(type.getSimpleName());
            }
            desc.append(joiner);

            throw new UnknownConstructorException("Could not find constructor '" + desc.toString() + "' in " + clazz);
        }
    }

}