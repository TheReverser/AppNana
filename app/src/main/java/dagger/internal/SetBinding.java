package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class SetBinding<T> extends Binding<Set<T>> {
    private final List<Binding<?>> contributors;
    private final SetBinding<T> parent;

    public static <T> void add(BindingsGroup bindings, String setKey, Binding<?> binding) {
        prepareSetBinding(bindings, setKey, binding).contributors.add(Linker.scope(binding));
    }

    private static <T> SetBinding<T> prepareSetBinding(BindingsGroup bindings, String setKey, Binding<?> binding) {
        Binding<?> previous = bindings.get(setKey);
        SetBinding<T> setBinding;
        if (previous instanceof SetBinding) {
            setBinding = (SetBinding) previous;
            boolean z = setBinding.library() && binding.library();
            setBinding.setLibrary(z);
            return setBinding;
        } else if (previous != null) {
            throw new IllegalArgumentException("Duplicate:\n    " + previous + "\n    " + binding);
        } else {
            setBinding = new SetBinding(setKey, binding.requiredBy);
            setBinding.setLibrary(binding.library());
            bindings.contributeSetBinding(setKey, setBinding);
            return (SetBinding) bindings.get(setKey);
        }
    }

    public SetBinding(String key, Object requiredBy) {
        super(key, null, false, requiredBy);
        this.parent = null;
        this.contributors = new ArrayList();
    }

    public SetBinding(SetBinding<T> original) {
        super(original.provideKey, null, false, original.requiredBy);
        this.parent = original;
        setLibrary(original.library());
        setDependedOn(original.dependedOn());
        this.contributors = new ArrayList();
    }

    public void attach(Linker linker) {
        for (Binding<?> contributor : this.contributors) {
            contributor.attach(linker);
        }
    }

    public int size() {
        int size = 0;
        for (SetBinding<T> binding = this; binding != null; binding = binding.parent) {
            size += binding.contributors.size();
        }
        return size;
    }

    public Set<T> get() {
        List<T> result = new ArrayList();
        for (SetBinding<T> setBinding = this; setBinding != null; setBinding = setBinding.parent) {
            int size = setBinding.contributors.size();
            for (int i = 0; i < size; i++) {
                Binding<?> contributor = (Binding) setBinding.contributors.get(i);
                Object contribution = contributor.get();
                if (contributor.provideKey.equals(this.provideKey)) {
                    result.addAll((Set) contribution);
                } else {
                    result.add(contribution);
                }
            }
        }
        return Collections.unmodifiableSet(new LinkedHashSet(result));
    }

    public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
        for (SetBinding<T> binding = this; binding != null; binding = binding.parent) {
            getBindings.addAll(binding.contributors);
        }
    }

    public void injectMembers(Set<T> set) {
        throw new UnsupportedOperationException("Cannot inject members on a contributed Set<T>.");
    }

    public String toString() {
        boolean first = true;
        StringBuilder builder = new StringBuilder("SetBinding[");
        for (SetBinding<T> setBinding = this; setBinding != null; setBinding = setBinding.parent) {
            int size = setBinding.contributors.size();
            for (int i = 0; i < size; i++) {
                if (!first) {
                    builder.append(",");
                }
                builder.append(setBinding.contributors.get(i));
                first = false;
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
