package osp.leobert.android.magicbox.type.infer.impl;


import osp.leobert.android.magicbox.type.infer.InferType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.infer.impl </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Implementation </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class Implementation implements InferType {
    private final Class<?> typeInterface;

    public Implementation(Class<?> typeInterface) {
        if (!typeInterface.isInterface())
            throw new IllegalArgumentException("typeInterface should be interface");
        this.typeInterface = typeInterface;
    }

    @Override
    public boolean infer(Class<?> clz) {
        if (clz == null)
            return false;

        return typeInterface.isAssignableFrom(clz);
    }

}
