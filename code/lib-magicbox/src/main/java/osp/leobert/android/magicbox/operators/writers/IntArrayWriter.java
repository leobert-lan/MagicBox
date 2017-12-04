package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> IntArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class IntArrayWriter implements BundleWriter {
    private static IntArrayWriter instance = null;

    private IntArrayWriter() {
        // single
    }

    public static IntArrayWriter getInstance() {
        if (instance == null)
            instance = new IntArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putIntArray(field.getBundleKey(), (int[]) propertyField.get(to));
    }
}
