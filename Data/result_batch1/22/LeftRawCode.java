// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/com/netease/LDNetDiagnoService/LDNetAsyncTaskEx.java#L57-L86
public class TempClass {
	    @SuppressWarnings("unchecked")
	    @Override
	    protected void done() {
		Message message;
		Result result = null;

		try {
		    result = get();
		} catch (InterruptedException e) {
		    android.util.Log.w(this.getClass().getSimpleName(), e);
		} catch (ExecutionException e) {
		    throw new RuntimeException(
			    "An error occured while executing doInBackground()",
			    e.getCause());
		} catch (CancellationException e) {
		    message = sHandler.obtainMessage(MESSAGE_POST_CANCEL,
			    new LDNetAsyncTaskResult<Result>(LDNetAsyncTaskEx.this,
				    (Result[]) null));
		    message.sendToTarget();
		    return;
		} catch (Throwable t) {
//		    throw new RuntimeException(
//			    "An error occured while executing "
//				    + "doInBackground()", t);
		}

		message = sHandler.obtainMessage(MESSAGE_POST_RESULT,
			new LDNetAsyncTaskResult<Result>(LDNetAsyncTaskEx.this, result));
		message.sendToTarget();
	    }

}