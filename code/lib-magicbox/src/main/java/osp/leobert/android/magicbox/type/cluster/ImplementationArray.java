package osp.leobert.android.magicbox.type.cluster;


import java.lang.reflect.Field;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ImplementationArray </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class ImplementationArray implements SupposeType {
    private final Class<?> typeInterface;

    public ImplementationArray(Class<?> typeInterface) {
        if (!typeInterface.isInterface())
            throw new IllegalArgumentException("typeInterface should be interface");
        this.typeInterface = typeInterface;
    }

    @Override
    public boolean check(Field field) {
        if (field == null)
            return false;
        if (!field.getType().isArray())
            return false;
        Class cc = field.getType().getComponentType();
        return typeInterface.isAssignableFrom(cc);
    }

    @Override
    public boolean canBeChecked() {
        return true;
    }
}
