package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ShortWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

public class ShortWriter implements BoxWriter {
    private static ShortWriter instance = null;

    private ShortWriter() {
        // single
    }

    public static ShortWriter getInstance() {
        if (instance == null)
            instance = new ShortWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putShort(field.getBundleKey(), propertyField.getShort(to));
    }
}
