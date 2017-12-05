package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.ArrayList;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> IntegerArrayListWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class IntegerArrayListWriter implements BundleWriter {
    private static IntegerArrayListWriter instance = null;

    private IntegerArrayListWriter() {
        // single
    }

    public static IntegerArrayListWriter getInstance() {
        if (instance == null)
            instance = new IntegerArrayListWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putIntegerArrayList(field.getBundleKey(), (ArrayList<Integer>) propertyField.get(to));

    }
}
