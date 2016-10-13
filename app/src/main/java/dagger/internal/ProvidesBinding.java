package dagger.internal;

public abstract class ProvidesBinding<T> extends Binding<T> {
    protected final String methodName;
    protected final String moduleClass;

    public abstract T get();

    public ProvidesBinding(String key, boolean singleton, String moduleClass, String methodName) {
        super(key, null, singleton, moduleClass + "." + methodName + "()");
        this.moduleClass = moduleClass;
        this.methodName = methodName;
    }

    public String toString() {
        return getClass().getName() + "[key=" + this.provideKey + " method=" + this.moduleClass + "." + this.methodName + "()" + "]";
    }
}
