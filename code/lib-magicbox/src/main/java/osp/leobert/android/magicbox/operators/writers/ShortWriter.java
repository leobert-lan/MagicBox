package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ShortWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/3.
 */

public class ShortWriter implements BundleWriter {
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