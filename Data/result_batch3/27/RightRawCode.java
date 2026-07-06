// https://github.com/HotswapProjects/HotswapAgent/tree/1074e9aaed7659654270faae951875cc443379c9/hotswap-agent-core/src/main/java/org/hotswap/agent/javassist/bytecode/annotation/AnnotationImpl.java#L129-L152
public class TempClass {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable
    {
        String name = method.getName();
        if (Object.class == method.getDeclaringClass()) {
            if ("equals".equals(name)) {
                Object obj = args[0];
                return Boolean.valueOf(checkEquals(obj));
            }
            else if ("toString".equals(name))
                return annotation.toString();
            else if ("hashCode".equals(name))
                return Integer.valueOf(hashCode());
        }
        else if ("annotationType".equals(name)
                 && method.getParameterTypes().length == 0)
           return getAnnotationType();

        MemberValue mv = annotation.getMemberValue(name);
        if (mv == null)
            return getDefault(name, method);
        return mv.getValue(classLoader, pool, method);
    }

}