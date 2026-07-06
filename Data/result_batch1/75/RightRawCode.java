// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/xposed-bridge/src/main/apacheCommonsLang/external/org/apache/commons/lang3/builder/EqualsBuilder.java#L385-L419
public class TempClass {
    private static void reflectionAppend(
        Object lhs,
        Object rhs,
        Class<?> clazz,
        EqualsBuilder builder,
        boolean useTransients,
        String[] excludeFields) {

        if (isRegistered(lhs, rhs)) {
            return;
        }

        try {
            register(lhs, rhs);
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length && builder.isEquals; i++) {
                Field f = fields[i];
                if (!ArrayUtils.contains(excludeFields, f.getName())
                    && (f.getName().indexOf('$') == -1)
                    && (useTransients || !Modifier.isTransient(f.getModifiers()))
                    && (!Modifier.isStatic(f.getModifiers()))) {
                    try {
                        builder.append(f.get(lhs), f.get(rhs));
                    } catch (IllegalAccessException e) {
                        //this can't happen. Would get a Security exception instead
                        //throw a runtime exception in case the impossible happens.
                        throw new InternalError("Unexpected IllegalAccessException");
                    }
                }
            }
        } finally {
            unregister(lhs, rhs);
        }
    }

}