package dagger.internal;

public abstract class Loader {
    private final Memoizer<ClassLoader, Memoizer<String, Class<?>>> caches = new Memoizer<ClassLoader, Memoizer<String, Class<?>>>() {
        protected Memoizer<String, Class<?>> create(final ClassLoader classLoader) {
            return new Memoizer<String, Class<?>>() {
                protected Class<?> create(String className) {
                    try {
                        return classLoader.loadClass(className);
                    } catch (ClassNotFoundException e) {
                        return Void.class;
                    }
                }
            };
        }
    };

    public abstract Binding<?> getAtInjectBinding(String str, String str2, ClassLoader classLoader, boolean z);

    public abstract <T> ModuleAdapter<T> getModuleAdapter(Class<T> cls);

    public abstract StaticInjection getStaticInjection(Class<?> cls);

    protected Class<?> loadClass(ClassLoader classLoader, String name) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return (Class) ((Memoizer) this.caches.get(classLoader)).get(name);
    }

    protected <T> T instantiate(String name, ClassLoader classLoader) {
        try {
            Class<?> generatedClass = loadClass(classLoader, name);
            if (generatedClass == Void.class) {
                return null;
            }
            return generatedClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to initialize " + name, e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to initialize " + name, e2);
        }
    }
}
