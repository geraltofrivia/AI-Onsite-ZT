// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-core/src/main/java/org/mockito/internal/creation/bytebuddy/InlineBytecodeGenerator.java#L473-L499
public class TempClass {
            @Override
            public MethodVisitor visitMethod(
                    int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor =
                        super.visitMethod(access, name, desc, signature, exceptions);
                MethodList<?> methodList =
                        typeDescription
                                .getDeclaredMethods()
                                .filter(
                                        (name.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)
                                                        ? isConstructor()
                                                        : ElementMatchers.<MethodDescription>named(
                                                                name))
                                                .and(hasDescriptor(desc)));
                if (methodList.size() == 1
                        && methodList.getOnly().getParameters().hasExplicitMetaData()) {
                    for (ParameterDescription parameterDescription :
                            methodList.getOnly().getParameters()) {
                        methodVisitor.visitParameter(
                                parameterDescription.getName(),
                                parameterDescription.getModifiers());
                    }
                    return new MethodParameterStrippingMethodVisitor(methodVisitor);
                } else {
                    return methodVisitor;
                }
            }

}