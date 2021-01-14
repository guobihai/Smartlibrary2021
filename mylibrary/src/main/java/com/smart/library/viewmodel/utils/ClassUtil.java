package com.smart.library.viewmodel.utils;
import com.smart.library.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author guobihai
 * 创建日期：2020/12/2
 * desc：
 */
public class ClassUtil {

    /**
     * 获取泛型ViewModel的class对象
     */
    public static <T> Class<T> getViewModel(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass);
        if (tClass == null || tClass == BaseViewModel.class) {
            return null;
        }
        return tClass;
    }

    private static <T> Class<T> getGenericClass(Class<?> klass) {
        Type type = klass.getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            Class<T> tClass = (Class<T>) t;
            if (BaseViewModel.class.isAssignableFrom(tClass)) {
                return tClass;
            }
        }
        return null;
    }


}
