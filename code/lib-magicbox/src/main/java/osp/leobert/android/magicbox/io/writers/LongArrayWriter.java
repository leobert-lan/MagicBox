package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> LongArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class LongArrayWriter implements BundleWriter {
    private static LongArrayWriter instance = null;

    private LongArrayWriter() {
        // single
    }

    public static LongArrayWriter getInstance() {
        if (instance == null)
            instance = new LongArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putLongArray(field.getBundleKey(), (long[]) propertyField.get(to));
    }
}
