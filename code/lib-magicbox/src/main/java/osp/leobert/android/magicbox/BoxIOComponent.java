package osp.leobert.android.magicbox;

import osp.leobert.android.magicbox.operators.BundleReader;
import osp.leobert.android.magicbox.operators.BundleWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BoxIOComponent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public interface BoxIOComponent extends ITypeCheck {
    BundleWriter getBundleWriter();
    BundleReader getBundleReader();
}
