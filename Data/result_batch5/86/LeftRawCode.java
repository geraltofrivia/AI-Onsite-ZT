// https://github.com/graphql-java/graphql-java/tree/23f078da4f181751f972d6ce071093fa8e661df7/src/main/java/graphql/util/InterThreadMemoizedSupplier.java#L25-L41
public class TempClass {
    @Override
    public T get() {
        if (!initialized) {
            lock.lock();
            try {
                if (initialized) {
                    return value;
                }
                value = delegate.get();
                initialized = true;
                return value;
            } finally {
                lock.unlock();
            }
        }
        return value;
    }

}