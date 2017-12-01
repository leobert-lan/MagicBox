package osp.leobert.android.magicbox.operators.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> DoubleReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class DoubleReader implements BundleReader {
    private static DoubleReader instance = null;

    private DoubleReader() {
        // single
    }

    public static DoubleReader getInstance() {
        if (instance == null)
            instance = new DoubleReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.setDouble(to,bundle.getDouble(field.getBundleKey()));

    }
}
