package osp.leobert.android.magicbox.fake;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.PersistableBundle;

import osp.leobert.android.magicbox.MagicBox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.proxy </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBoxInstrumentation </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/12.
 */

public class MagicBoxInstrumentation extends Instrumentation {

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        MagicBox.getInstance().saveInstanceState(activity, outState);
        super.callActivityOnSaveInstanceState(activity, outState);
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState, PersistableBundle outPersistentState) {
        MagicBox.getInstance().saveInstanceState(activity, outState);
        super.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState);
        MagicBox.getInstance().restoreInstanceState(activity, savedInstanceState);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState, PersistableBundle persistentState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
        MagicBox.getInstance().restoreInstanceState(activity, savedInstanceState);
    }
}
