package osp.leobert.android.magicbox.type.infer.impl;


import osp.leobert.android.magicbox.type.infer.InferType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.infer.impl </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ImplementationArray </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class ImplementationArray implements InferType {
    private final Class<?> typeInterface;

    public ImplementationArray(Class<?> typeInterface) {
        if (!typeInterface.isInterface())
            throw new IllegalArgumentException("typeInterface should be interface");
        this.typeInterface = typeInterface;
    }

    @Override
    public boolean infer(Class<?> clz) {
        if (clz == null)
            return false;
        if (!clz.isArray())
            return false;
        Class cc = clz.getComponentType();
        return typeInterface.isAssignableFrom(cc);
    }
}
