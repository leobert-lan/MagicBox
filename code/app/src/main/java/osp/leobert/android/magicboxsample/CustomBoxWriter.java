package osp.leobert.android.magicboxsample;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BaseCustomBoxWriter;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CustomBoxWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public class CustomBoxWriter extends BaseCustomBoxWriter {
    private static CustomBoxWriter instance = null;

    private CustomBoxWriter() {
        // single
    }

    public static CustomBoxWriter getInstance() {
        if (instance == null)
            instance = new CustomBoxWriter();
        return instance;
    }

    @Override
    protected void parseAndSave(Bundle bundle, String bundleKey, Object o) {
        Gson gson = new Gson();
        String _s = gson.toJson(o);

        bundle.putString(bundleKey, _s);
    }
}
