// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/io/onedev/server/util/ReflectionUtils.java#L357-L387
public class TempClass {
	@SuppressWarnings("unchecked")
	public static <T> Constructor<T> findConstructor(Class<T> clazz, Class<?>... expectedParamTypes) {
		if (expectedParamTypes.length == 0) {
			try {
				return clazz.getConstructor();
			} catch (Exception e) {
				throw ExceptionUtils.unchecked(e);
			}
		}
		
		Constructor<?>[] constructors = clazz.getConstructors();
		
		for (Constructor<?> constructor : constructors) {
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if (paramTypes.length != expectedParamTypes.length) 
				continue;
			
			boolean found = true;
			for (int i = 0; i < paramTypes.length; i++) {
				if (!paramTypes[i].isAssignableFrom(expectedParamTypes[i])) {
					found = false;
					break;
				}
			}
			
			if (found) 
				return (Constructor<T>) constructor;
		}
		
		return null;
	}

}