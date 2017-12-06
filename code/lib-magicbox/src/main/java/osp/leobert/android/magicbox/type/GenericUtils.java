package osp.leobert.android.magicbox.type;

import java.lang.reflect.*;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> GenericUtils </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/6.
 */

public class GenericUtils {
    private static Class getClass(java.lang.reflect.Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return getClass(((TypeVariable) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {
            // class本身也是type，强制转型
            return (Class) type;
        }
    }

    public static Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            return (Class) genericClass;
        }
    }
}
