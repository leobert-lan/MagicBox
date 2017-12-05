package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ParcelableArrayListReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class ParcelableArrayListReader implements BoxReader {
    private static ParcelableArrayListReader instance = null;

    private ParcelableArrayListReader() {
        // single
    }

    public static ParcelableArrayListReader getInstance() {
        if (instance == null)
            instance = new ParcelableArrayListReader();
        return instance;
    }
    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to,bundle.getParcelableArrayList(field.getBundleKey()));
    }
}
