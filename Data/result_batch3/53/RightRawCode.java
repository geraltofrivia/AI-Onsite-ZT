// https://github.com/Konloch/bytecode-viewer/tree/903ac6a3fd19f1a3047789a91fb35e48d60459fb/src/main/java/the/bytecode/club/bytecodeviewer/decompilers/impl/CFRDecompiler.java#L73-L105
public class TempClass {
    private String decompile(ClassNode cn, String name, byte[] content)
    {
        String exception;

        try
        {
            String classPath = name + (name.endsWith(CLASS_SUFFIX) ? "" : CLASS_SUFFIX);
            StringBuilder builder = new StringBuilder();
            Consumer<SinkReturns.Decompiled> dumpDecompiled = d -> builder.append(d.getJava());

            //initialize CFR
            Options options = generateOptions();
            ClassFileSource source = new BCVDataSource(options, cn, classPath, content);
            CfrDriver driver = new CfrDriver.Builder().withClassFileSource(source).withBuiltOptions(options).withOutputSink(new BCVOutputSinkFactory(dumpDecompiled)).build();

            //decompile the class-file
            driver.analyse(Collections.singletonList(name));

            //handle simulated errors
            if(Constants.DEV_FLAG_DECOMPILERS_SIMULATED_ERRORS)
                throw new RuntimeException(DEV_MODE_SIMULATED_ERROR.toString());

            //return the builder contents
            return builder.toString();
        }
        catch (Throwable e)
        {
            exception = ExceptionUtils.exceptionToString(e);
        }

        return CFR + " " + ERROR + "! " + ExceptionUI.SEND_STACKTRACE_TO + NL + NL
            + TranslatedStrings.SUGGESTED_FIX_DECOMPILER_ERROR + NL + NL + exception;
    }

}