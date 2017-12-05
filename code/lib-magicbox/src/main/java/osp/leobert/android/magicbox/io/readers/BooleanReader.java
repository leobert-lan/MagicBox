package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;


/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.io.readers </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BooleanReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public class BooleanReader implements BoxReader {

    private static BoxReader instance;

    private BooleanReader() {
    }

    public static BoxReader getInstance() {
        if (instance == null) {
            instance = new BooleanReader();
        }
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.setBoolean(to,bundle.getBoolean(field.getBundleKey()));
    }
}