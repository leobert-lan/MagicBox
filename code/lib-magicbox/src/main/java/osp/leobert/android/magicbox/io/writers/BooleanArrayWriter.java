package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BooleanArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class BooleanArrayWriter implements BundleWriter {
    private static BooleanArrayWriter instance = null;

    private BooleanArrayWriter() {
        // single
    }

    public static BooleanArrayWriter getInstance() {
        if (instance == null)
            instance = new BooleanArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putBooleanArray(field.getBundleKey(), (boolean[]) propertyField.get(to));
    }
}
