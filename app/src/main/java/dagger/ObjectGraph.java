package dagger;

import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.FailoverLoader;
import dagger.internal.Keys;
import dagger.internal.Linker;
import dagger.internal.Loader;
import dagger.internal.ModuleAdapter;
import dagger.internal.Modules;
import dagger.internal.ProblemDetector;
import dagger.internal.SetBinding;
import dagger.internal.StaticInjection;
import dagger.internal.ThrowingErrorHandler;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ObjectGraph {

    static class DaggerObjectGraph extends ObjectGraph {
        private final DaggerObjectGraph base;
        private final Map<String, Class<?>> injectableTypes;
        private final Linker linker;
        private final Loader plugin;
        private final List<SetBinding<?>> setBindings;
        private final Map<Class<?>, StaticInjection> staticInjections;

        DaggerObjectGraph(DaggerObjectGraph base, Linker linker, Loader plugin, Map<Class<?>, StaticInjection> staticInjections, Map<String, Class<?>> injectableTypes, List<SetBinding<?>> setBindings) {
            this.base = base;
            this.linker = (Linker) checkNotNull(linker, "linker");
            this.plugin = (Loader) checkNotNull(plugin, "plugin");
            this.staticInjections = (Map) checkNotNull(staticInjections, "staticInjections");
            this.injectableTypes = (Map) checkNotNull(injectableTypes, "injectableTypes");
            this.setBindings = (List) checkNotNull(setBindings, "setBindings");
        }

        private static <T> T checkNotNull(T object, String label) {
            if (object != null) {
                return object;
            }
            throw new NullPointerException(label);
        }

        private static ObjectGraph makeGraph(DaggerObjectGraph base, Loader plugin, Object... modules) {
            Map<String, Class<?>> injectableTypes = new LinkedHashMap();
            Map<Class<?>, StaticInjection> staticInjections = new LinkedHashMap();
            BindingsGroup baseBindings = base == null ? new StandardBindings() : new StandardBindings(base.setBindings);
            BindingsGroup overrideBindings = new OverridesBindings();
            for (Entry<ModuleAdapter<?>, Object> loadedModule : Modules.loadModules(plugin, modules).entrySet()) {
                ModuleAdapter<Object> moduleAdapter = (ModuleAdapter) loadedModule.getKey();
                for (Object put : moduleAdapter.injectableTypes) {
                    injectableTypes.put(put, moduleAdapter.moduleClass);
                }
                for (Object put2 : moduleAdapter.staticInjections) {
                    staticInjections.put(put2, null);
                }
                try {
                    BindingsGroup addTo;
                    if (moduleAdapter.overrides) {
                        addTo = overrideBindings;
                    } else {
                        addTo = baseBindings;
                    }
                    moduleAdapter.getBindings(addTo, loadedModule.getValue());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(moduleAdapter.moduleClass.getSimpleName() + ": " + e.getMessage(), e);
                }
            }
            Linker linker = new Linker(base != null ? base.linker : null, plugin, new ThrowingErrorHandler());
            linker.installBindings(baseBindings);
            linker.installBindings(overrideBindings);
            return new DaggerObjectGraph(base, linker, plugin, staticInjections, injectableTypes, baseBindings.setBindings);
        }

        public ObjectGraph plus(Object... modules) {
            linkEverything();
            return makeGraph(this, this.plugin, modules);
        }

        private void linkStaticInjections() {
            for (Entry<Class<?>, StaticInjection> entry : this.staticInjections.entrySet()) {
                StaticInjection staticInjection = (StaticInjection) entry.getValue();
                if (staticInjection == null) {
                    staticInjection = this.plugin.getStaticInjection((Class) entry.getKey());
                    entry.setValue(staticInjection);
                }
                staticInjection.attach(this.linker);
            }
        }

        private void linkInjectableTypes() {
            for (Entry<String, Class<?>> entry : this.injectableTypes.entrySet()) {
                this.linker.requestBinding((String) entry.getKey(), entry.getValue(), ((Class) entry.getValue()).getClassLoader(), false, true);
            }
        }

        public void validate() {
            new ProblemDetector().detectProblems(linkEverything().values());
        }

        private Map<String, Binding<?>> linkEverything() {
            Map<String, Binding<?>> bindings = this.linker.fullyLinkedBindings();
            if (bindings != null) {
                return bindings;
            }
            synchronized (this.linker) {
                bindings = this.linker.fullyLinkedBindings();
                if (bindings != null) {
                    return bindings;
                }
                linkStaticInjections();
                linkInjectableTypes();
                Map<String, Binding<?>> linkAll = this.linker.linkAll();
                return linkAll;
            }
        }

        public void injectStatics() {
            synchronized (this.linker) {
                linkStaticInjections();
                this.linker.linkRequested();
                linkStaticInjections();
            }
            for (Entry<Class<?>, StaticInjection> entry : this.staticInjections.entrySet()) {
                ((StaticInjection) entry.getValue()).inject();
            }
        }

        public <T> T get(Class<T> type) {
            String key = Keys.get(type);
            return getInjectableTypeBinding(type.getClassLoader(), type.isInterface() ? key : Keys.getMembersKey(type), key).get();
        }

        public <T> T inject(T instance) {
            String membersKey = Keys.getMembersKey(instance.getClass());
            getInjectableTypeBinding(instance.getClass().getClassLoader(), membersKey, membersKey).injectMembers(instance);
            return instance;
        }

        private Binding<?> getInjectableTypeBinding(ClassLoader classLoader, String injectableKey, String key) {
            Class<?> moduleClass = null;
            for (DaggerObjectGraph graph = this; graph != null; graph = graph.base) {
                moduleClass = (Class) graph.injectableTypes.get(injectableKey);
                if (moduleClass != null) {
                    break;
                }
            }
            if (moduleClass == null) {
                throw new IllegalArgumentException("No inject registered for " + injectableKey + ". You must explicitly add it to the 'injects' option in one of your modules.");
            }
            Binding<?> binding;
            synchronized (this.linker) {
                binding = this.linker.requestBinding(key, moduleClass, classLoader, false, true);
                if (binding == null || !binding.isLinked()) {
                    this.linker.linkRequested();
                    binding = this.linker.requestBinding(key, moduleClass, classLoader, false, true);
                }
            }
            return binding;
        }
    }

    private static final class OverridesBindings extends BindingsGroup {
        OverridesBindings() {
        }

        public Binding<?> contributeSetBinding(String key, SetBinding<?> setBinding) {
            throw new IllegalArgumentException("Module overrides cannot contribute set bindings.");
        }
    }

    private static final class StandardBindings extends BindingsGroup {
        private final List<SetBinding<?>> setBindings;

        public StandardBindings() {
            this.setBindings = new ArrayList();
        }

        public StandardBindings(List<SetBinding<?>> baseSetBindings) {
            this.setBindings = new ArrayList(baseSetBindings.size());
            for (SetBinding<?> sb : baseSetBindings) {
                SetBinding<?> child = new SetBinding(sb);
                this.setBindings.add(child);
                put(child.provideKey, child);
            }
        }

        public Binding<?> contributeSetBinding(String key, SetBinding<?> value) {
            this.setBindings.add(value);
            return super.put(key, value);
        }
    }

    public abstract <T> T get(Class<T> cls);

    public abstract <T> T inject(T t);

    public abstract void injectStatics();

    public abstract ObjectGraph plus(Object... objArr);

    public abstract void validate();

    ObjectGraph() {
    }

    public static ObjectGraph create(Object... modules) {
        return DaggerObjectGraph.makeGraph(null, new FailoverLoader(), modules);
    }

    static ObjectGraph createWith(Loader loader, Object... modules) {
        return DaggerObjectGraph.makeGraph(null, loader, modules);
    }
}
