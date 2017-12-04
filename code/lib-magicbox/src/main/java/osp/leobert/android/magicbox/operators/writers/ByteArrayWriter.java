package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ByteArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class ByteArrayWriter implements BundleWriter {
    private static ByteArrayWriter instance = null;

    private ByteArrayWriter() {
        // single
    }

    public static ByteArrayWriter getInstance() {
        if (instance == null)
            instance = new ByteArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putByteArray(field.getBundleKey(), (byte[]) propertyField.get(to));

    }
}
