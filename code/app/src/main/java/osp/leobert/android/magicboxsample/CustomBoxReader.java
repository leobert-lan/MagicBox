package osp.leobert.android.magicboxsample;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CustomBoxReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public class CustomBoxReader implements BoxReader {
    private static CustomBoxReader instance = null;

    private CustomBoxReader() {
        // single
    }

    public static CustomBoxReader getInstance() {
        if (instance == null)
            instance = new CustomBoxReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);

        String s = bundle.getString(field.getBundleKey());
        Gson gson = new Gson();

        propertyField.set(to,gson.fromJson(s,propertyField.getType()));

    }
}
