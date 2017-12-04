package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;
import android.util.Size;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SizeWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class SizeWriter implements BundleWriter {
    private static SizeWriter instance = null;

    private SizeWriter() {
        // single
    }

    public static SizeWriter getInstance() {
        if (instance == null)
            instance = new SizeWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putSize(field.getBundleKey(), (Size) propertyField.get(to));

    }
}