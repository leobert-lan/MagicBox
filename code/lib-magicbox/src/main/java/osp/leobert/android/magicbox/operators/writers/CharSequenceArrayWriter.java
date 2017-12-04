package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CharSequenceArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class CharSequenceArrayWriter implements BundleWriter {
    private static CharSequenceArrayWriter instance = null;

    private CharSequenceArrayWriter() {
        // single
    }

    public static CharSequenceArrayWriter getInstance() {
        if (instance == null)
            instance = new CharSequenceArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putCharSequenceArray(field.getBundleKey(), (CharSequence[]) propertyField.get(to));
    }
}
