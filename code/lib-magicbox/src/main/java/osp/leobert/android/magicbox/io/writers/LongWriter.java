package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> LongWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

public class LongWriter implements BundleWriter {
    private static LongWriter instance = null;

    private LongWriter() {
        // single
    }

    public static LongWriter getInstance() {
        if (instance == null)
            instance = new LongWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putLong(field.getBundleKey(), propertyField.getLong(to));
    }
}
