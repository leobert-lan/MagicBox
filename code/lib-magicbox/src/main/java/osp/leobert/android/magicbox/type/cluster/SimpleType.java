package osp.leobert.android.magicbox.type.cluster;


import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type.cluster </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Simple </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/19.
 */

public class SimpleType implements SupposeType {
//    private final String type;

    private final Class<?> type;

    public SimpleType(Class<?> type) {
        this.type = type;
    }

    @Override
    public boolean check(Class clz) {
//        if (clz == null)
//            return false;
//        return type != null && type.equals(clz.getName());
        //simplify to follow code

        return clz != null && type != null && type.getName().equals(clz.getName());

    }

    @Override
    public boolean canBeChecked() {
        return true;
    }
}
