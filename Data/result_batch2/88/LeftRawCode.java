// https://github.com/BoltsFramework/Bolts-Android/tree/54e9cb8bdd4950aa4d418dcbc0ea65414762aef5/bolts-tasks/src/main/java/bolts/Task.java#L521-L570
public class TempClass {
  public static Task<Void> whenAll(Collection<? extends Task<?>> tasks) {
    if (tasks.size() == 0) {
      return Task.forResult(null);
    }

    final bolts.TaskCompletionSource<Void> allFinished = new bolts.TaskCompletionSource<>();
    final ArrayList<Exception> causes = new ArrayList<>();
    final Object errorLock = new Object();
    final AtomicInteger count = new AtomicInteger(tasks.size());
    final AtomicBoolean isCancelled = new AtomicBoolean(false);

    for (Task<?> task : tasks) {
      @SuppressWarnings("unchecked")
      Task<Object> t = (Task<Object>) task;
      t.continueWith(new Continuation<Object, Void>() {
        @Override
        public Void then(Task<Object> task) {
          if (task.isFaulted()) {
            synchronized (errorLock) {
              causes.add(task.getError());
            }
          }

          if (task.isCancelled()) {
            isCancelled.set(true);
          }

          if (count.decrementAndGet() == 0) {
            if (causes.size() != 0) {
              if (causes.size() == 1) {
                allFinished.setError(causes.get(0));
              } else {
                Exception error = new AggregateException(
                    String.format("There were %d exceptions.", causes.size()),
                    causes);
                allFinished.setError(error);
              }
            } else if (isCancelled.get()) {
              allFinished.setCancelled();
            } else {
              allFinished.setResult(null);
            }
          }
          return null;
        }
      });
    }

    return allFinished.getTask();
  }

}