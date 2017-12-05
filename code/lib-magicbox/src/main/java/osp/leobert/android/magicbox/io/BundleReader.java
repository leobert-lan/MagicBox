package osp.leobert.android.magicbox.io;

import android.os.Bundle;

import osp.leobert.android.magicbox.model.StateField;


/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.io </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BundleReader </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public interface BundleReader {
    void read(Bundle bundle, Object to, StateField field) throws IllegalAccessException;
}
