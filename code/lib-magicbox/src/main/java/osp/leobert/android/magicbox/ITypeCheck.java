package osp.leobert.android.magicbox;

import osp.leobert.android.magicbox.type.infer.impl.NegativeInfer;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> ITypeCheck </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

interface ITypeCheck {
    boolean canBeChecked();

    boolean check(Class clz);
}
