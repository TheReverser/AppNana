package dagger.internal;

final class BuiltInBinding<T> extends Binding<T> {
    private final ClassLoader classLoader;
    private Binding<?> delegate;
    private final String delegateKey;

    public BuiltInBinding(String key, Object requiredBy, ClassLoader classLoader, String delegateKey) {
        super(key, null, false, requiredBy);
        this.classLoader = classLoader;
        this.delegateKey = delegateKey;
    }

    public void attach(Linker linker) {
        this.delegate = linker.requestBinding(this.delegateKey, this.requiredBy, this.classLoader);
    }

    public void injectMembers(T t) {
        throw new UnsupportedOperationException();
    }

    public T get() {
        return this.delegate;
    }

    public Binding<?> getDelegate() {
        return this.delegate;
    }
}
