// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/ReflectionUtils.java#L161-L175
public class TempClass {
	public static Field findField(Class<?> clazz, String fieldName) {
		Field field = null;
		Class<?> current = clazz;
		while (current != null) {
			for (Field declaredField: current.getDeclaredFields()) {
				if (!Modifier.isStatic(declaredField.getModifiers()) 
						&& fieldName.equals(declaredField.getName())) {
					field = declaredField;
					break;
				}
			}
			current = current.getSuperclass();
		}
		return field;
	}

}