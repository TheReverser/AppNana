package dagger.internal;

import dagger.Lazy;
import dagger.MembersInjector;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import javax.inject.Provider;
import javax.inject.Qualifier;

public final class Keys {
    private static final Memoizer<Class<? extends Annotation>, Boolean> IS_QUALIFIER_ANNOTATION = new Memoizer<Class<? extends Annotation>, Boolean>() {
        protected Boolean create(Class<? extends Annotation> annotationType) {
            return Boolean.valueOf(annotationType.isAnnotationPresent(Qualifier.class));
        }
    };
    private static final String LAZY_PREFIX = (Lazy.class.getCanonicalName() + "<");
    private static final String MEMBERS_INJECTOR_PREFIX = (MembersInjector.class.getCanonicalName() + "<");
    private static final String PROVIDER_PREFIX = (Provider.class.getCanonicalName() + "<");
    private static final String SET_PREFIX = (Set.class.getCanonicalName() + "<");

    Keys() {
    }

    public static String get(Type type) {
        return get(type, null);
    }

    public static String getMembersKey(Class<?> key) {
        return "members/".concat(key.getName());
    }

    private static String get(Type type, Annotation annotation) {
        type = boxIfPrimitive(type);
        if (annotation == null && (type instanceof Class) && !((Class) type).isArray()) {
            return ((Class) type).getName();
        }
        StringBuilder result = new StringBuilder();
        if (annotation != null) {
            result.append(annotation).append("/");
        }
        typeToString(type, result, true);
        return result.toString();
    }

    public static String getSetKey(Type type, Annotation[] annotations, Object subject) {
        Annotation qualifier = extractQualifier(annotations, subject);
        type = boxIfPrimitive(type);
        StringBuilder result = new StringBuilder();
        if (qualifier != null) {
            result.append(qualifier).append("/");
        }
        result.append(SET_PREFIX);
        typeToString(type, result, true);
        result.append(">");
        return result.toString();
    }

    public static String get(Type type, Annotation[] annotations, Object subject) {
        return get(type, extractQualifier(annotations, subject));
    }

    private static Annotation extractQualifier(Annotation[] annotations, Object subject) {
        Annotation qualifier = null;
        for (Annotation a : annotations) {
            if (((Boolean) IS_QUALIFIER_ANNOTATION.get(a.annotationType())).booleanValue()) {
                if (qualifier != null) {
                    throw new IllegalArgumentException("Too many qualifier annotations on " + subject);
                }
                qualifier = a;
            }
        }
        return qualifier;
    }

    private static void typeToString(Type type, StringBuilder result, boolean topLevel) {
        if (type instanceof Class) {
            Class<?> c = (Class) type;
            if (c.isArray()) {
                typeToString(c.getComponentType(), result, false);
                result.append("[]");
            } else if (!c.isPrimitive()) {
                result.append(c.getName());
            } else if (topLevel) {
                throw new UnsupportedOperationException("Uninjectable type " + c.getName());
            } else {
                result.append(c.getName());
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            typeToString(parameterizedType.getRawType(), result, true);
            Type[] arguments = parameterizedType.getActualTypeArguments();
            result.append("<");
            for (int i = 0; i < arguments.length; i++) {
                if (i != 0) {
                    result.append(", ");
                }
                typeToString(arguments[i], result, true);
            }
            result.append(">");
        } else if (type instanceof GenericArrayType) {
            typeToString(((GenericArrayType) type).getGenericComponentType(), result, false);
            result.append("[]");
        } else {
            throw new UnsupportedOperationException("Uninjectable type " + type);
        }
    }

    static String getBuiltInBindingsKey(String key) {
        int start = startOfType(key);
        if (substringStartsWith(key, start, PROVIDER_PREFIX)) {
            return extractKey(key, start, key.substring(0, start), PROVIDER_PREFIX);
        }
        if (substringStartsWith(key, start, MEMBERS_INJECTOR_PREFIX)) {
            return extractKey(key, start, "members/", MEMBERS_INJECTOR_PREFIX);
        }
        return null;
    }

    static String getLazyKey(String key) {
        int start = startOfType(key);
        if (substringStartsWith(key, start, LAZY_PREFIX)) {
            return extractKey(key, start, key.substring(0, start), LAZY_PREFIX);
        }
        return null;
    }

    private static int startOfType(String key) {
        return key.startsWith("@") ? key.lastIndexOf(47) + 1 : 0;
    }

    private static String extractKey(String key, int start, String delegatePrefix, String prefix) {
        return delegatePrefix + key.substring(prefix.length() + start, key.length() - 1);
    }

    private static boolean substringStartsWith(String string, int offset, String substring) {
        return string.regionMatches(offset, substring, 0, substring.length());
    }

    public static boolean isAnnotated(String key) {
        return key.startsWith("@");
    }

    public static String getClassName(String key) {
        int start = 0;
        if (key.startsWith("@") || key.startsWith("members/")) {
            start = key.lastIndexOf(47) + 1;
        }
        return (key.indexOf(60, start) == -1 && key.indexOf(91, start) == -1) ? key.substring(start) : null;
    }

    public static boolean isPlatformType(String name) {
        return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.");
    }

    private static Type boxIfPrimitive(Type type) {
        if (type == Byte.TYPE) {
            return Byte.class;
        }
        if (type == Short.TYPE) {
            return Short.class;
        }
        if (type == Integer.TYPE) {
            return Integer.class;
        }
        if (type == Long.TYPE) {
            return Long.class;
        }
        if (type == Character.TYPE) {
            return Character.class;
        }
        if (type == Boolean.TYPE) {
            return Boolean.class;
        }
        if (type == Float.TYPE) {
            return Float.class;
        }
        if (type == Double.TYPE) {
            return Double.class;
        }
        if (type == Void.TYPE) {
            return Void.class;
        }
        return type;
    }
}
