package dagger.internal;

public abstract class ModuleAdapter<T> {
    public final boolean complete;
    public final Class<?>[] includes;
    public final String[] injectableTypes;
    public final boolean library;
    public final Class<T> moduleClass;
    public final boolean overrides;
    public final Class<?>[] staticInjections;

    protected ModuleAdapter(Class<T> moduleClass, String[] injectableTypes, Class<?>[] staticInjections, boolean overrides, Class<?>[] includes, boolean complete, boolean library) {
        this.moduleClass = moduleClass;
        this.injectableTypes = injectableTypes;
        this.staticInjections = staticInjections;
        this.overrides = overrides;
        this.includes = includes;
        this.complete = complete;
        this.library = library;
    }

    public void getBindings(BindingsGroup map, T t) {
    }

    protected T newModule() {
        throw new UnsupportedOperationException("No no-args constructor on " + getClass().getName());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModuleAdapter)) {
            return false;
        }
        return this.moduleClass.equals(((ModuleAdapter) obj).moduleClass);
    }

    public final int hashCode() {
        return this.moduleClass.hashCode();
    }
}
