package osp.leobert.android.magicbox.type.infer.impl;

import osp.leobert.android.magicbox.type.infer.InferType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.infer.impl </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> UnSupportInfer </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class UnSupportInfer implements InferType {
    @Override
    public boolean infer(Class<?> clz) {
        return false;
    }
}
