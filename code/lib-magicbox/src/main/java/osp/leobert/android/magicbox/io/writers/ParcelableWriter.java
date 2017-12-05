package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ParcelableWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class ParcelableWriter implements BoxWriter {
    private static ParcelableWriter instance = null;

    private ParcelableWriter() {
        // single
    }

    public static ParcelableWriter getInstance() {
        if (instance == null)
            instance = new ParcelableWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putParcelable(field.getBundleKey(), (Parcelable) propertyField.get(to));
    }
}
