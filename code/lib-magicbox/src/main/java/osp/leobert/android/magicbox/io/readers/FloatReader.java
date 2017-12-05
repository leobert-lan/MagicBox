package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> FloatReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class FloatReader implements BundleReader {
    private static FloatReader instance = null;

    private FloatReader() {
        // single
    }

    public static FloatReader getInstance() {
        if (instance == null)
            instance = new FloatReader();
        return instance;
    }
    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.setFloat(to,bundle.getFloat(field.getBundleKey()));

    }
}
