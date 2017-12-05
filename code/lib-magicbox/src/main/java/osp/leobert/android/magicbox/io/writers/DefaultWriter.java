package osp.leobert.android.magicbox.io.writers;

import android.os.Bundle;

import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.io.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io.writers </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> DefaultWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

public class DefaultWriter implements BundleWriter {
    private static DefaultWriter instance = null;

    private DefaultWriter() {
        // single
    }

    public static DefaultWriter getInstance() {
        if (instance == null)
            instance = new DefaultWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        // TODO: 2017/12/4 debuglog
    }
}
