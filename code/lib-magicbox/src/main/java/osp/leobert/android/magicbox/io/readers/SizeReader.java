package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SizeReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class SizeReader implements BundleReader {

    private static SizeReader instance = null;

    private SizeReader() {
        // single
    }

    public static SizeReader getInstance() {
        if (instance == null)
            instance = new SizeReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.set(to,bundle.getSize(field.getBundleKey()));
    }
}
