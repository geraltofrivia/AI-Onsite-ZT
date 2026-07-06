// https://github.com/BoltsFramework/Bolts-Android/tree/54e9cb8bdd4950aa4d418dcbc0ea65414762aef5/bolts-tasks/src/main/java/bolts/Task.java#L906-L942
public class TempClass {
        @Override
        public void run() {
          if (ct != null && ct.isCancellationRequested()) {
            tcs.setCancelled();
            return;
          }

          try {
            Task<TContinuationResult> result = continuation.then(task);
            if (result == null) {
              tcs.setResult(null);
            } else {
              result.continueWith(new Continuation<TContinuationResult, Void>() {
                @Override
                public Void then(Task<TContinuationResult> task) {
                  if (ct != null && ct.isCancellationRequested()) {
                    tcs.setCancelled();
                    return null;
                  }

                  if (task.isCancelled()) {
                    tcs.setCancelled();
                  } else if (task.isFaulted()) {
                    tcs.setError(task.getError());
                  } else {
                    tcs.setResult(task.getResult());
                  }
                  return null;
                }
              });
            }
          } catch (CancellationException e) {
            tcs.setCancelled();
          } catch (Exception e) {
            tcs.setError(e);
          }
        }

}