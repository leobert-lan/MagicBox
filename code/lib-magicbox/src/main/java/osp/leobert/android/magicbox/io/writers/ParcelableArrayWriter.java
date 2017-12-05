package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ParcelableArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class ParcelableArrayWriter implements BundleWriter {
    private static ParcelableArrayWriter instance = null;

    private ParcelableArrayWriter() {
        // single
    }

    public static ParcelableArrayWriter getInstance() {
        if (instance == null)
            instance = new ParcelableArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putParcelableArray(field.getBundleKey(), (Parcelable[]) propertyField.get(to));
    }
}
