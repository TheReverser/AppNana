package dagger.internal.loaders;

import dagger.internal.Binding;
import dagger.internal.Binding.InvalidBindingException;
import dagger.internal.Keys;
import dagger.internal.Linker;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

public final class ReflectiveAtInjectBinding<T> extends Binding<T> {
    private final Constructor<T> constructor;
    private final Binding<?>[] fieldBindings;
    private final Field[] fields;
    private final String[] keys;
    private final ClassLoader loader;
    private final Binding<?>[] parameterBindings;
    private final Class<?> supertype;
    private Binding<? super T> supertypeBinding;

    private ReflectiveAtInjectBinding(String provideKey, String membersKey, boolean singleton, Class<?> type, Field[] fields, Constructor<T> constructor, int parameterCount, Class<?> supertype, String[] keys) {
        super(provideKey, membersKey, singleton, type);
        this.constructor = constructor;
        this.fields = fields;
        this.supertype = supertype;
        this.keys = keys;
        this.parameterBindings = new Binding[parameterCount];
        this.fieldBindings = new Binding[fields.length];
        this.loader = type.getClassLoader();
    }

    public void attach(Linker linker) {
        int i;
        int k = 0;
        for (i = 0; i < this.fields.length; i++) {
            if (this.fieldBindings[i] == null) {
                this.fieldBindings[i] = linker.requestBinding(this.keys[k], this.fields[i], this.loader);
            }
            k++;
        }
        if (this.constructor != null) {
            for (i = 0; i < this.parameterBindings.length; i++) {
                if (this.parameterBindings[i] == null) {
                    this.parameterBindings[i] = linker.requestBinding(this.keys[k], this.constructor, this.loader);
                }
                k++;
            }
        }
        if (this.supertype != null && this.supertypeBinding == null) {
            this.supertypeBinding = linker.requestBinding(this.keys[k], this.membersKey, this.loader, false, true);
        }
    }

    public T get() {
        if (this.constructor == null) {
            throw new UnsupportedOperationException();
        }
        Object[] args = new Object[this.parameterBindings.length];
        for (int i = 0; i < this.parameterBindings.length; i++) {
            args[i] = this.parameterBindings[i].get();
        }
        try {
            T result = this.constructor.newInstance(args);
            injectMembers(result);
            return result;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            throw (cause instanceof RuntimeException ? (RuntimeException) cause : new RuntimeException(cause));
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        }
    }

    public void injectMembers(T t) {
        int i = 0;
        while (i < this.fields.length) {
            try {
                this.fields[i].set(t, this.fieldBindings[i].get());
                i++;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        if (this.supertypeBinding != null) {
            this.supertypeBinding.injectMembers(t);
        }
    }

    public void getDependencies(Set<Binding<?>> get, Set<Binding<?>> injectMembers) {
        if (this.parameterBindings != null) {
            Collections.addAll(get, this.parameterBindings);
        }
        Collections.addAll(injectMembers, this.fieldBindings);
        if (this.supertypeBinding != null) {
            injectMembers.add(this.supertypeBinding);
        }
    }

    public String toString() {
        return this.provideKey != null ? this.provideKey : this.membersKey;
    }

    public static <T> Binding<T> create(Class<T> type, boolean mustHaveInjections) {
        String provideKey;
        int parameterCount;
        boolean singleton = type.isAnnotationPresent(Singleton.class);
        List<String> keys = new ArrayList();
        List<Field> injectedFields = new ArrayList();
        for (Class<?> c = type; c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class) && !Modifier.isStatic(field.getModifiers())) {
                    if ((field.getModifiers() & 2) != 0) {
                        throw new IllegalStateException("Can't inject private field: " + field);
                    }
                    field.setAccessible(true);
                    injectedFields.add(field);
                    keys.add(Keys.get(field.getGenericType(), field.getAnnotations(), field));
                }
            }
        }
        Constructor<T> injectedConstructor = null;
        for (Constructor<T> constructor : getConstructorsForType(type)) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                if (injectedConstructor != null) {
                    throw new InvalidBindingException(type.getName(), "has too many injectable constructors");
                }
                injectedConstructor = constructor;
            }
        }
        if (injectedConstructor == null) {
            if (!injectedFields.isEmpty()) {
                try {
                    injectedConstructor = type.getDeclaredConstructor(new Class[0]);
                } catch (NoSuchMethodException e) {
                }
            } else if (mustHaveInjections) {
                throw new InvalidBindingException(type.getName(), "has no injectable members. Do you want to add an injectable constructor?");
            }
        }
        if (injectedConstructor == null) {
            provideKey = null;
            parameterCount = 0;
            if (singleton) {
                throw new IllegalArgumentException("No injectable constructor on @Singleton " + type.getName());
            }
        } else if ((injectedConstructor.getModifiers() & 2) != 0) {
            throw new IllegalStateException("Can't inject private constructor: " + injectedConstructor);
        } else {
            provideKey = Keys.get(type);
            injectedConstructor.setAccessible(true);
            Type[] types = injectedConstructor.getGenericParameterTypes();
            parameterCount = types.length;
            if (parameterCount != 0) {
                Annotation[][] annotations = injectedConstructor.getParameterAnnotations();
                for (int p = 0; p < types.length; p++) {
                    keys.add(Keys.get(types[p], annotations[p], injectedConstructor));
                }
            }
        }
        Class<? super T> supertype = type.getSuperclass();
        if (supertype != null) {
            if (Keys.isPlatformType(supertype.getName())) {
                supertype = null;
            } else {
                keys.add(Keys.getMembersKey(supertype));
            }
        }
        return new ReflectiveAtInjectBinding(provideKey, Keys.getMembersKey(type), singleton, type, (Field[]) injectedFields.toArray(new Field[injectedFields.size()]), injectedConstructor, parameterCount, supertype, (String[]) keys.toArray(new String[keys.size()]));
    }

    private static <T> Constructor<T>[] getConstructorsForType(Class<T> type) {
        return type.getDeclaredConstructors();
    }
}
