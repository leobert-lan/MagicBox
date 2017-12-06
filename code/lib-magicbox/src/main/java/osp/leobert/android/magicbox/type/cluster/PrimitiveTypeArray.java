package osp.leobert.android.magicbox.type.cluster;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.cluster </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BasicOrBoxArray </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class PrimitiveTypeArray implements SupposeType {

    private final Class<?> primitiveComponentClz;

    public PrimitiveTypeArray(Class<?> primitiveComponentClz) {
        this.primitiveComponentClz = primitiveComponentClz;
    }

    @Override
    public boolean check(Field field) {
        if (field == null || !field.getType().isArray())
            return false;

//        String arrayName = clz.getName();
        Class componentClass = field.getType().getComponentType();
        String componentName = componentClass.getName();

        return primitiveComponentClz.getName().equals(componentName);
    }


    @Override
    public boolean canBeChecked() {
        return true;
    }
}
