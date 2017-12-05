package osp.leobert.android.magicbox.operators.writers;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Field;
import java.util.ArrayList;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.operators.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ParcelableArrayListWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ParcelableArrayListWriter implements BundleWriter {
    private static ParcelableArrayListWriter instance = null;

    private ParcelableArrayListWriter() {
        // single
    }

    public static ParcelableArrayListWriter getInstance() {
        if (instance == null)
            instance = new ParcelableArrayListWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        bundle.putParcelableArrayList(field.getBundleKey(), (ArrayList<? extends Parcelable>) propertyField.get(to));

    }
}
