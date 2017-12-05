package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CharWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

public class CharWriter implements BoxWriter {
    private static CharWriter instance = null;

    private CharWriter() {
        // single
    }

    public static CharWriter getInstance() {
        if (instance == null)
            instance = new CharWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putChar(field.getBundleKey(), propertyField.getChar(to));
    }
}
