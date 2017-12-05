package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CharSequenceWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class CharSequenceWriter implements BoxWriter {
    private static CharSequenceWriter instance = null;

    private CharSequenceWriter() {
        // single
    }

    public static CharSequenceWriter getInstance() {
        if (instance == null)
            instance = new CharSequenceWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putCharSequence(field.getBundleKey(), (CharSequence) propertyField.get(to));
    }
}
