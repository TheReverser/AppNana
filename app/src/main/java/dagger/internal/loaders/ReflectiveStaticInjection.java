package dagger.internal.loaders;

import dagger.internal.Binding;
import dagger.internal.Keys;
import dagger.internal.Linker;
import dagger.internal.StaticInjection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public final class ReflectiveStaticInjection extends StaticInjection {
    private Binding<?>[] bindings;
    private final Field[] fields;
    private final ClassLoader loader;

    private ReflectiveStaticInjection(ClassLoader loader, Field[] fields) {
        this.fields = fields;
        this.loader = loader;
    }

    public void attach(Linker linker) {
        this.bindings = new Binding[this.fields.length];
        for (int i = 0; i < this.fields.length; i++) {
            Field field = this.fields[i];
            this.bindings[i] = linker.requestBinding(Keys.get(field.getGenericType(), field.getAnnotations(), field), field, this.loader);
        }
    }

    public void inject() {
        int f = 0;
        while (f < this.fields.length) {
            try {
                this.fields[f].set(null, this.bindings[f].get());
                f++;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static StaticInjection create(Class<?> injectedClass) {
        List<Field> fields = new ArrayList();
        for (Field field : injectedClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                fields.add(field);
            }
        }
        if (!fields.isEmpty()) {
            return new ReflectiveStaticInjection(injectedClass.getClassLoader(), (Field[]) fields.toArray(new Field[fields.size()]));
        }
        throw new IllegalArgumentException("No static injections: " + injectedClass.getName());
    }
}
