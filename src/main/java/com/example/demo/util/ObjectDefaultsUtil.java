package com.example.demo.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObjectDefaultsUtil {

    private static final Set<Class<?>> PRIMITIVE_TYPES = getPrimitiveTypes();

    /**
     * 设置对象的默认值
     *
     * @param obj 要设置默认值的对象
     * @param <T> 对象的类型
     */
    public static <T> void setDefaultValues(T obj) {
        if (obj == null) {
            return;
        }
        setDefaultValuesInternal(obj, new HashSet<>());
    }

    /**
     * 内部递归方法设置对象的默认值
     *
     * @param obj            要设置默认值的对象
     * @param visitedObjects 已访问的对象集合
     */
    private static void setDefaultValuesInternal(Object obj, Set<Object> visitedObjects) {
        if (visitedObjects.contains(obj)) {
            return;
        }
        visitedObjects.add(obj);

        Class<?> clazz = obj.getClass();
        if (clazz.isPrimitive() || PRIMITIVE_TYPES.contains(clazz)) {
            return; // 跳过基本类型和常见类型的包装类
        }

        if (clazz.isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(obj, i);
                setDefaultValuesInternal(element, visitedObjects); // 递归处理数组元素
            }
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue; // 跳过静态和常量字段
            }

            field.setAccessible(true);
            try {
                Object fieldValue = field.get(obj);
                if (fieldValue == null) {
                    Class<?> fieldType = field.getType();
                    if (fieldType == Long.class || fieldType == long.class) {
                        field.set(obj, 0L);
                    } else if (fieldType == Integer.class || fieldType == int.class) {
                        field.set(obj, 0);
                    } else if (fieldType == Double.class || fieldType == double.class) {
                        field.set(obj, 0.0);
                    } else if (fieldType == BigDecimal.class) {
                        field.set(obj, BigDecimal.ZERO);
                    } else if (fieldType == String.class) {
                        field.set(obj, "");
                    } else {
                        Object defaultValue = fieldType.getDeclaredConstructor().newInstance();
                        field.set(obj, defaultValue); // 设置对象类型属性的默认值
                        setDefaultValuesInternal(defaultValue, visitedObjects); // 递归处理嵌套对象的属性
                    }
                } else {
                    setDefaultValuesInternal(fieldValue, visitedObjects); // 递归处理嵌套对象的属性
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置对象列表中对象的默认值
     *
     * @param list 对象列表
     * @param <T>  对象的类型
     */
    public static <T> void setDefaultValues(List<T> list) {
        if (list == null) {
            return;
        }
        for (T obj : list) {
            setDefaultValues(obj);
        }
    }

    /**
     * 获取基本类型和常见类型的包装类
     *
     * @return 类型集合
     */
    private static Set<Class<?>> getPrimitiveTypes() {
        Set<Class<?>> types = new HashSet<>();
        types.add(Boolean.class);
        types.add(Byte.class);
        types.add(Character.class);
        types.add(Short.class);
        types.add(Integer.class);
        types.add(Long.class);
        types.add(Float.class);
        types.add(Double.class);
        types.add(Void.class);
        types.add(String.class);
        types.add(BigDecimal.class);
        return types;
    }
}
