// https://github.com/Piasy/BigImageViewer/tree/067f8eb86ee522190ab473f36a1b0de11c21a663/BigImageViewer/src/main/java/com/github/piasy/biv/utils/ThreadedCallbacks.java#L61-L82
public class TempClass {
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws Throwable {
        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new RuntimeException("Method should return void: " + method);
        }
        if (Looper.myLooper() == mHandler.getLooper()) {
            method.invoke(mTarget, args);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        method.invoke(mTarget, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return NON_SENSE;
    }

}