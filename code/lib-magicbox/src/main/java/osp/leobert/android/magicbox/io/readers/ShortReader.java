package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ShortReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class ShortReader implements BundleReader {
    private static ShortReader instance = null;

    private ShortReader() {
        // single
    }

    public static ShortReader getInstance() {
        if (instance == null)
            instance = new ShortReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        propertyField.setShort(to,bundle.getShort(field.getBundleKey()));
    }
}
