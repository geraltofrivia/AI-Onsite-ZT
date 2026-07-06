// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/xposed-bridge/src/main/java/de/robv/android/xposed/XposedHelpers.java#L99-L118
public class TempClass {
	public static Field findField(Class<?> clazz, String fieldName) {
		String fullFieldName = clazz.getName() + '#' + fieldName;

		if (fieldCache.containsKey(fullFieldName)) {
			Field field = fieldCache.get(fullFieldName);
			if (field == null)
				throw new NoSuchFieldError(fullFieldName);
			return field;
		}

		try {
			Field field = findFieldRecursiveImpl(clazz, fieldName);
			field.setAccessible(true);
			fieldCache.put(fullFieldName, field);
			return field;
		} catch (NoSuchFieldException e) {
			fieldCache.put(fullFieldName, null);
			throw new NoSuchFieldError(fullFieldName);
		}
	}

}