// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-core/src/main/java/org/mockito/internal/matchers/apachecommons/EqualsBuilder.java#L283-L315
public class TempClass {
    private static boolean reflectionAppend(
            Object lhs,
            Object rhs,
            Class<?> clazz,
            EqualsBuilder builder,
            boolean useTransients,
            String[] excludeFields) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> excludedFieldList =
                excludeFields != null
                        ? Arrays.asList(excludeFields)
                        : Collections.<String>emptyList();
        MemberAccessor accessor = Plugins.getMemberAccessor();
        for (int i = 0; i < fields.length && builder.isEquals; i++) {
            Field f = fields[i];
            if (!excludedFieldList.contains(f.getName())
                    && (f.getName().indexOf('$') == -1)
                    && (useTransients || !Modifier.isTransient(f.getModifiers()))
                    && !Modifier.isStatic(f.getModifiers())) {
                try {
                    builder.append(accessor.get(f, lhs), accessor.get(f, rhs));
                } catch (RuntimeException | IllegalAccessException ignored) {
                    // In this case, we tried to test a subclass vs. a superclass and
                    // the subclass has ivars or the ivars are transient and we are
                    // testing transients. If a subclass has ivars that we are trying
                    // to test them, we get an exception and we know that the objects
                    // are not equal.
                    return true;
                }
            }
        }
        return false;
    }

}