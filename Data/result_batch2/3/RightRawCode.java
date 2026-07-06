// https://github.com/ElderDrivers/EdXposed/tree/8f2308cdde0a8151a2b4b0151c58b203154a8c6f/xposed-bridge/src/main/java/de/robv/android/xposed/XposedHelpers.java#L466-L476
public class TempClass {
	public static Method findMethodBestMatch(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object[] args) {
		Class<?>[] argsClasses = null;
		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i] != null)
				continue;
			if (argsClasses == null)
				argsClasses = getParameterTypes(args);
			parameterTypes[i] = argsClasses[i];
		}
		return findMethodBestMatch(clazz, methodName, parameterTypes);
	}

}