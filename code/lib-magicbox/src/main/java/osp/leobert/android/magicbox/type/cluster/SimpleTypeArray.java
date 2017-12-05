package osp.leobert.android.magicbox.type.cluster;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.cluster </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BasicOrBoxArray </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class SimpleTypeArray implements SupposeType {

    private final Class<?> simpleType;

    public SimpleTypeArray(Class<?> simpleType) {
        this.simpleType = simpleType;
    }

    @Override
    public boolean check(Class<?> clz) {
        if (clz == null || !clz.isArray())
            return false;

//        String arrayName = clz.getName();
        Class componentClass = clz.getComponentType();
        String componentName = componentClass.getName();

        return simpleType.getName().equals(componentName);
    }


    @Override
    public boolean canBeChecked() {
        return true;
    }
}
