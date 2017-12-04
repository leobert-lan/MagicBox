package osp.leobert.android.magicbox.operators;

import android.os.Bundle;

import osp.leobert.android.magicbox.model.StateField;


/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.operators </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Operator </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public class Operator {

    public void writeToBundle(Bundle bundle, Object from, StateField field) {
        try {
            field.getType().getBundleWriter()
                    .write(bundle,from,field);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void readFromBundle(Bundle bundle, Object to, StateField field) {
        try {
            field.getType().getBundleReader()
                    .read(bundle, to, field);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
