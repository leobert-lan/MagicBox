package osp.leobert.android.magicbox.operators;

import android.os.Bundle;

import osp.leobert.android.magicbox.model.StateField;


/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.operators </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> BundleWriter </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public interface BundleWriter {
    void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException;

}
