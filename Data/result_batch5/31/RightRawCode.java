// https://github.com/openjdk/jmh/tree/2a316030b509aa9874dd6ab04e21962ac92cd634/jmh-generator-asm/src/main/java/org/openjdk/jmh/generators/asm/ASMClassInfo.java#L132-L142
public class TempClass {
    @Override
    public MethodVisitor visitMethod(int access, final String methodName, String methodDesc, String signature, String[] exceptions) {
        ASMMethodInfo mi = new ASMMethodInfo(super.visitMethod(access, methodName, methodDesc, signature, exceptions),
                classInfos, this, access, methodName, methodDesc, signature);
        if (methodName.equals("<init>")) {
            constructors.add(mi);
        } else {
            methods.add(mi);
        }
        return mi;
    }

}