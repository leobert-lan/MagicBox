package osp.leobert.android.magicbox.type.cluster;

import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> UnSupportInfer </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class NegativeInfer implements SupposeType {
    @Override
    public boolean check(Class<?> clz) {
        return false;
    }

    @Override
    public boolean canBeChecked() {
        return false;
    }
}
