package osp.leobert.android.magicbox.type.infer.impl;

import android.text.TextUtils;

import osp.leobert.android.magicbox.type.infer.InferType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.infer.impl </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BasicOrBoxArray </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class PrimitiveOrItsBoxedTypeArray implements InferType {

    private final Class<?> primitiveComponentClz;

    private final Class<?> boxedComponentClz;

    public PrimitiveOrItsBoxedTypeArray(Class<?> primitiveComponentClz, Class<?> boxedComponentClz) {
        this.primitiveComponentClz = primitiveComponentClz;
        this.boxedComponentClz = boxedComponentClz;
    }

    @Override
    public boolean infer(Class<?> clz) {
        if (clz == null || !clz.isArray())
            return false;

//        String arrayName = clz.getName();
        Class componentClass = clz.getComponentType();
        String componentName = componentClass.getName();

        return primitiveComponentClz.getName().equals(componentName) ||
                boxedComponentClz.getName().equals(componentName);
    }


}
