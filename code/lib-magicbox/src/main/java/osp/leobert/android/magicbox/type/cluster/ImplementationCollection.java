package osp.leobert.android.magicbox.type.cluster;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ImplementationCollection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/6.
 */

public class ImplementationCollection implements SupposeType {

    private Class<?> collectionType;




    @Override
    public boolean canBeChecked() {
        return true;
    }

    @Override
    public boolean check(Class<?> clz) {
        if (clz == null)
            return false;

        Type type = clz;

        if (type instanceof ParameterizedType)


        return false;
    }
}
