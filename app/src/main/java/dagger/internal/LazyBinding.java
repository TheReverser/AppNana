package dagger.internal;

import dagger.Lazy;

final class LazyBinding<T> extends Binding<Lazy<T>> {
    private static final Object NOT_PRESENT = new Object();
    private Binding<T> delegate;
    private final String lazyKey;
    private final ClassLoader loader;

    public LazyBinding(String key, Object requiredBy, ClassLoader loader, String lazyKey) {
        super(key, null, false, requiredBy);
        this.loader = loader;
        this.lazyKey = lazyKey;
    }

    public void attach(Linker linker) {
        this.delegate = linker.requestBinding(this.lazyKey, this.requiredBy, this.loader);
    }

    public void injectMembers(Lazy<T> lazy) {
        throw new UnsupportedOperationException();
    }

    public Lazy<T> get() {
        return new Lazy<T>() {
            private volatile Object cacheValue = LazyBinding.NOT_PRESENT;

            public T get() {
                if (this.cacheValue == LazyBinding.NOT_PRESENT) {
                    synchronized (this) {
                        if (this.cacheValue == LazyBinding.NOT_PRESENT) {
                            this.cacheValue = LazyBinding.this.delegate.get();
                        }
                    }
                }
                return this.cacheValue;
            }
        };
    }
}
