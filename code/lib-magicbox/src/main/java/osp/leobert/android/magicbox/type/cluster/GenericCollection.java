package osp.leobert.android.magicbox.type.cluster;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import osp.leobert.android.magicbox.type.GenericUtils;
import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> GenericCollection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/6.
 */

abstract class GenericCollection implements SupposeType {
    private final Class<?> collectionType;

    private final Class<?>[] genericTypes;

    GenericCollection(@NonNull Class<?> collectionType, @NonNull Class<?>[] genericTypes) {
        this.collectionType = collectionType;
        this.genericTypes = genericTypes;
    }

    @Override
    public final boolean canBeChecked() {
        return true;
    }

    @Override
    public final boolean check(Field field) {
        if (field == null)
            return false;

        Type type = field.getGenericType();

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();

            if (rawType.equals(collectionType)) {
                return checkGenericType(parameterizedType);
            } else
                return false;
        }
        return false;
    }

    private boolean checkGenericType(ParameterizedType type) {
        int size = type.getActualTypeArguments().length;
        if (size != genericTypes.length)
            return false;
        for (int i = 0; i < size; i++) {
            Class<?> genericClass = GenericUtils.getGenericClass(type, i);
            if (!supposeTo(genericTypes[i], genericClass))
                return false;
        }
        return true;
    }

    protected abstract boolean supposeTo(Class<?> suppose, Class<?> toBeCheck);
}
