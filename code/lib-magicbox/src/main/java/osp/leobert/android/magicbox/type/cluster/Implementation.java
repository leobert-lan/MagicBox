package osp.leobert.android.magicbox.type.cluster;


import java.lang.reflect.Field;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.cluster </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Implementation </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class Implementation implements SupposeType {
    private final Class<?> typeInterface;

    public Implementation(Class<?> typeInterface) {
        if (!typeInterface.isInterface())
            throw new IllegalArgumentException("typeInterface should be interface");
        this.typeInterface = typeInterface;
    }

    @Override
    public boolean canBeChecked() {
        return true;
    }

    @Override
    public boolean check(Field field) {
        if (field == null)
            return false;

        return typeInterface.isAssignableFrom(field.getType());
    }

}
