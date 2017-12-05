package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ParcelableArrayReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class ParcelableArrayReader implements BundleReader {
    private static ParcelableArrayReader instance = null;

    private ParcelableArrayReader() {
        // single
    }

    public static ParcelableArrayReader getInstance() {
        if (instance == null)
            instance = new ParcelableArrayReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to,bundle.getParcelableArray(field.getBundleKey()));
    }
}
