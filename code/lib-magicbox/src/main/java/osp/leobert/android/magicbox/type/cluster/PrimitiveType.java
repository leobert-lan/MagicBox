package osp.leobert.android.magicbox.type.cluster;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> PrimitiveType </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class PrimitiveType extends SimpleType {
    public PrimitiveType(Class<?> type) {
        super(type);
        if (!type.isPrimitive())
            throw new IllegalArgumentException("should init with a primitive type,but not "+ type.getName());
    }
}
