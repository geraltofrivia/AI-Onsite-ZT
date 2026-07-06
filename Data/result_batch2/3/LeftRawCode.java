// https://github.com/ssssssss-team/spider-flow/tree/c799cca99c7d064673dc79e6ef06a08bbc0e292d/spider-flow-core/src/main/java/org/spiderflow/core/expression/interpreter/JavaReflection.java#L192-L236
public class TempClass {
	private static Method findMethod (List<Method> methods, Class<?>[] parameterTypes) {
		Method foundMethod = null;
		int foundScore = 0;
		for (Method method : methods) {
			// Check if the types match.
			Class<?>[] otherTypes = method.getParameterTypes();
			if(parameterTypes.length != otherTypes.length){
				continue;
			}
			boolean match = true;
			int score = 0;
			for (int ii = 0, nn = parameterTypes.length; ii < nn; ii++) {
				Class<?> type = parameterTypes[ii];
				Class<?> otherType = otherTypes[ii];

				if (!otherType.isAssignableFrom(type)) {
					score++;
					if (!isPrimitiveAssignableFrom(type, otherType)) {
						score++;
						if (!isCoercible(type, otherType)) {
							match = false;
							break;
						} else {
							score++;
						}
					}
				}else if(type == null && otherType.isPrimitive()){
					match = false;
					break;
				}
			}
			if (match) {
				if (foundMethod == null) {
					foundMethod = method;
					foundScore = score;
				} else {
					if (score < foundScore) {
						foundScore = score;
						foundMethod = method;
					}
				}
			}
		}
		return foundMethod;
	}

}