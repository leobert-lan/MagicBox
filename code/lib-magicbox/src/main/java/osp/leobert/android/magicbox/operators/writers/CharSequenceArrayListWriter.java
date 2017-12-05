package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.ArrayList;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CharSequenceArrayListWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

@SuppressWarnings("unchecked")
public class CharSequenceArrayListWriter implements BundleWriter {
    private static CharSequenceArrayListWriter instance = null;

    private CharSequenceArrayListWriter() {
        // single
    }

    public static CharSequenceArrayListWriter getInstance() {
        if (instance == null)
            instance = new CharSequenceArrayListWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putCharSequenceArrayList(field.getBundleKey(), (ArrayList<CharSequence>) propertyField.get(to));

    }
}
