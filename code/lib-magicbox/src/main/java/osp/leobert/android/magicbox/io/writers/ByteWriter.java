package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ByteWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

public class ByteWriter implements BundleWriter {
    private static ByteWriter instance = null;

    private ByteWriter() {
        // single
    }

    public static ByteWriter getInstance() {
        if (instance == null)
            instance = new ByteWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putByte(field.getBundleKey(), propertyField.getByte(to));
    }
}
