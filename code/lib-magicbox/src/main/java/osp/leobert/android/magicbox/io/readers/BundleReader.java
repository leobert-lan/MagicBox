package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxReader;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BundleReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/12.
 */

public class BundleReader implements BoxReader {
    private static BundleReader instance = null;

    private BundleReader() {
        // single
    }

    public static BundleReader getInstance() {
        if (instance == null)
            instance = new BundleReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to, bundle.getBundle(field.getBundleKey()));
    }
}
