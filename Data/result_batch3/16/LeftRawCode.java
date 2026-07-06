// https://github.com/j-easy/easy-rules/tree/d4450831533e47a5c0cb361edb80dad43db228a4/easy-rules-core/src/main/java/org/jeasy/rules/core/RuleDefinitionValidator.java#L120-L145
public class TempClass {
    private boolean validParameters(final Method method) {
        int notAnnotatedParameterCount = 0;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] annotations : parameterAnnotations) {
            if (annotations.length == 0) {
                notAnnotatedParameterCount += 1;
            } else {
                //Annotation types has to be Fact
                for (Annotation annotation : annotations) {
                    if (!annotation.annotationType().equals(Fact.class)) {
                        return false;
                    }
                }
            }
        }
        if (notAnnotatedParameterCount > 1) {
            return false;
        }
        if (notAnnotatedParameterCount == 1) {
            Parameter notAnnotatedParameter = getNotAnnotatedParameter(method);
            if (notAnnotatedParameter != null) {
                return Facts.class.isAssignableFrom(notAnnotatedParameter.getType());
            }
        }
        return true;
    }

}