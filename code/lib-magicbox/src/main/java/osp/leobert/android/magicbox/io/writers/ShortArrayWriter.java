package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ShortArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class ShortArrayWriter implements BoxWriter {
    private static ShortArrayWriter instance = null;

    private ShortArrayWriter() {
        // single
    }

    public static ShortArrayWriter getInstance() {
        if (instance == null)
            instance = new ShortArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putShortArray(field.getBundleKey(), (short[]) propertyField.get(to));

    }
}
