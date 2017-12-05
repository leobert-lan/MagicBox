package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;


/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.io.writers </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BooleanWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public class BooleanWriter implements BundleWriter {

    private static BundleWriter instance = null;

    private BooleanWriter() {
        // single
    }

    public static BundleWriter getInstance() {
        if (instance == null)
            instance = new BooleanWriter();
        return instance;
    }


    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putBoolean(field.getBundleKey(), propertyField.getBoolean(to));
    }
}
