package osp.leobert.android.magicbox.io;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BaseCustomBoxReader </p>
 * <p><b>Description:</b> the base type for custom reader </p>
 * Created by leobert on 2017/12/5.
 */

public abstract class BaseCustomBoxReader implements BoxReader {

    @Override
    public final void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);

        String bundleKey = field.getBundleKey();
        propertyField.set(to,fetchAndParse(bundle,bundleKey,propertyField.getType()));
    }

    protected abstract Object fetchAndParse(Bundle bundle, String bundleKey, Class<?> type);
}
