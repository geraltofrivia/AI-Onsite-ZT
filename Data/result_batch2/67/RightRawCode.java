// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/LongTaskAPI/src/main/java/org/gephi/utils/longtask/api/LongTaskExecutor.java#L403-L423
public class TempClass {
        @Override
        public void run() {
            if (task != null) {
                if (task.future != null) {
                    task.future.cancel(interruptCancel);
                }
                if (cancelTimer != null) {
                    cancelTimer.cancel();
                }
                cancelTimer = null;
                if (task.progress != null) {
                    task.progress.finish();
                }
                finished(task);

                if (!inBackground) {
                    Logger.getLogger("").warning("Task from " + name + " did not respond to cancellation request. Interrupting thread.");
                    Thread.currentThread().interrupt();
                }
            }
        }

}