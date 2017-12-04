package osp.leobert.android.magicbox.type.infer.impl;


import osp.leobert.android.magicbox.type.infer.InferType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.infer.impl </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> PrimitiveOrItsBoxedType </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class PrimitiveOrItsBoxedType implements InferType {
    private final String box;

    private final String basic;

    public PrimitiveOrItsBoxedType(String box, String basic) {
        this.box = box;
        this.basic = basic;
    }

    @Override
    public boolean infer(Class<?> clz) {
        if (clz == null)
            return false;

        if (clz.isPrimitive()) {
            return basic != null && basic.equals(clz.getName());
        } else
            return box != null && box.equals(clz.getName());
    }
}
