package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.io.Serializable;
import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SerializableWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class SerializableWriter implements BoxWriter {
    private static SerializableWriter instance = null;

    private SerializableWriter() {
        // single
    }

    public static SerializableWriter getInstance() {
        if (instance == null)
            instance = new SerializableWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putSerializable(field.getBundleKey(), (Serializable) propertyField.get(to));
    }
}
