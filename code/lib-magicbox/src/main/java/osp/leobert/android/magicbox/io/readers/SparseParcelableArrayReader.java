package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SparseParcelableArrayReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class SparseParcelableArrayReader implements BoxReader {
    private static SparseParcelableArrayReader instance = null;

    private SparseParcelableArrayReader() {
        // single
    }

    public static SparseParcelableArrayReader getInstance() {
        if (instance == null)
            instance = new SparseParcelableArrayReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to,bundle.getSparseParcelableArray(field.getBundleKey()));
    }
}
