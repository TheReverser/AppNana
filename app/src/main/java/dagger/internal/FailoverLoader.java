package dagger.internal;

import dagger.internal.loaders.GeneratedAdapters;
import dagger.internal.loaders.ReflectiveAtInjectBinding;
import dagger.internal.loaders.ReflectiveStaticInjection;

public final class FailoverLoader extends Loader {
    private final Memoizer<Class<?>, ModuleAdapter<?>> loadedAdapters = new Memoizer<Class<?>, ModuleAdapter<?>>() {
        protected ModuleAdapter<?> create(Class<?> type) {
            ModuleAdapter<?> result = (ModuleAdapter) FailoverLoader.this.instantiate(type.getName().concat(GeneratedAdapters.MODULE_ADAPTER_SUFFIX), type.getClassLoader());
            if (result != null) {
                return result;
            }
            throw new IllegalStateException("Module adapter for " + type + " could not be loaded. " + "Please ensure that code generation was run for this module.");
        }
    };

    public <T> ModuleAdapter<T> getModuleAdapter(Class<T> type) {
        return (ModuleAdapter) this.loadedAdapters.get(type);
    }

    public Binding<?> getAtInjectBinding(String key, String className, ClassLoader classLoader, boolean mustHaveInjections) {
        Binding<?> result = (Binding) instantiate(className.concat(GeneratedAdapters.INJECT_ADAPTER_SUFFIX), classLoader);
        if (result != null) {
            return result;
        }
        Class<?> type = loadClass(classLoader, className);
        if (type.equals(Void.class)) {
            throw new IllegalStateException(String.format("Could not load class %s needed for binding %s", new Object[]{className, key}));
        } else if (type.isInterface()) {
            return null;
        } else {
            return ReflectiveAtInjectBinding.create(type, mustHaveInjections);
        }
    }

    public StaticInjection getStaticInjection(Class<?> injectedClass) {
        StaticInjection result = (StaticInjection) instantiate(injectedClass.getName().concat(GeneratedAdapters.STATIC_INJECTION_SUFFIX), injectedClass.getClassLoader());
        return result != null ? result : ReflectiveStaticInjection.create(injectedClass);
    }
}
