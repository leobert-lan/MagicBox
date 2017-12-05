package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> FloatArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class FloatArrayWriter implements BundleWriter {
    private static FloatArrayWriter instance = null;

    private FloatArrayWriter() {
        // single
    }

    public static FloatArrayWriter getInstance() {
        if (instance == null)
            instance = new FloatArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putFloatArray(field.getBundleKey(), (float[]) propertyField.get(to));
    }
}
