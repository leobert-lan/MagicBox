package osp.leobert.android.magicboxsample;

import android.app.Application;

import osp.leobert.android.magicbox.MagicBox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MyApplication </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/7.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MagicBox.setLogEnable(true);
        MagicBox.getInstance().globalDelegateMode();
    }
}
