package osp.leobert.android.magicboxsample;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.io.BaseCustomBoxReader;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BoxReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> CustomBoxReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public class CustomBoxReader extends BaseCustomBoxReader {
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
    protected Object fetchAndParse(Bundle bundle, String bundleKey, Class<?> type) {
        String s = bundle.getString(bundleKey);
        Gson gson = new Gson();

        return gson.fromJson(s,type);
    }

}
