package osp.leobert.android.magicbox.type.cluster;

import android.support.annotation.NonNull;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SimpleCollection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/6.
 */

public class SimpleCollection extends GenericCollection {
    public SimpleCollection(@NonNull Class<?> collectionType, @NonNull Class<?>... genericTypes) {
        super(collectionType, genericTypes);
    }

    @Override
    protected boolean supposeTo(Class<?> suppose, Class<?> toBeCheck) {
        return suppose.equals(toBeCheck);
    }
}
