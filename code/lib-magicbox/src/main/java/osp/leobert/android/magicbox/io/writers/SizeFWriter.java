package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;
import android.util.SizeF;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SizeFWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class SizeFWriter implements BoxWriter {
    private static SizeFWriter instance = null;

    private SizeFWriter() {
        // single
    }

    public static SizeFWriter getInstance() {
        if (instance == null)
            instance = new SizeFWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putSizeF(field.getBundleKey(), (SizeF) propertyField.get(to));
    }
}
