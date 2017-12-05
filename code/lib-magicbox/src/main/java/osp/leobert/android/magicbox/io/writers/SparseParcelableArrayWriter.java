package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SparseParcelableArrayWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class SparseParcelableArrayWriter implements BoxWriter {
    private static SparseParcelableArrayWriter instance = null;

    private SparseParcelableArrayWriter() {
        // single
    }

    public static SparseParcelableArrayWriter getInstance() {
        if (instance == null)
            instance = new SparseParcelableArrayWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putSparseParcelableArray(field.getBundleKey(), (SparseArray<? extends Parcelable>) propertyField.get(to));

    }
}
