package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CharSequenceArrayList </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class CharSequenceArrayListReader implements BoxReader {
    private static CharSequenceArrayListReader instance = null;

    private CharSequenceArrayListReader() {
        // single
    }

    public static CharSequenceArrayListReader getInstance() {
        if (instance == null)
            instance = new CharSequenceArrayListReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to,bundle.getCharSequenceArrayList(field.getBundleKey()));
    }
}
