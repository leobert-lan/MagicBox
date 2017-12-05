package osp.leobert.android.magicbox.io;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BaseCustomBoxWriter </p>
 * <p><b>Description:</b> the base type for custom writer </p>
 * Created by leobert on 2017/12/5.
 */

public abstract class BaseCustomBoxWriter implements BoxWriter {
    @Override
    public final void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        parseAndSave(bundle,field.getBundleKey(),propertyField.get(to));
    }

    protected abstract void parseAndSave(Bundle bundle, String bundleKey, Object o);
}
