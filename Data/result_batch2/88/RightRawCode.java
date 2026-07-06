// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCLCore/src/main/java/org/jackhuang/hmcl/task/AsyncTaskExecutor.java#L98-L116
public class TempClass {
    private CompletableFuture<?> executeTasksExceptionally(Task<?> parentTask, Collection<? extends Task<?>> tasks) {
        if (tasks == null || tasks.isEmpty())
            return CompletableFuture.completedFuture(null);

        return CompletableFuture.completedFuture(null)
                .thenComposeAsync(unused -> {
                    totTask.addAndGet(tasks.size());

                    if (isCancelled()) {
                        for (Task<?> task : tasks) task.setException(new CancellationException());
                        return CompletableFuture.runAsync(this::checkCancellation);
                    }

                    return CompletableFuture.allOf(tasks.stream()
                            .map(task -> CompletableFuture.completedFuture(null)
                                    .thenComposeAsync(unused2 -> executeTask(parentTask, task))
                            ).toArray(CompletableFuture<?>[]::new));
                });
    }

}