package osp.leobert.android.magicbox.io.readers;

import android.os.Bundle;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleReader;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.readers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> DefaultReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class DefaultReader implements BundleReader {
    private static DefaultReader instance = null;

    private DefaultReader() {
        // single
    }

    public static DefaultReader getInstance() {
        if (instance == null)
            instance = new DefaultReader();
        return instance;
    }

    @Override
    public void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        // TODO: 2017/12/4 debuglog
    }
}
