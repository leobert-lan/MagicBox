package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BundleWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/12.
 */

public class BundleWriter implements BoxWriter {
    private static BundleWriter instance = null;

    private BundleWriter() {
        // single
    }

    public static BundleWriter getInstance() {
        if (instance == null)
            instance = new BundleWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putBundle(field.getBundleKey(), (Bundle) propertyField.get(to));

    }
}