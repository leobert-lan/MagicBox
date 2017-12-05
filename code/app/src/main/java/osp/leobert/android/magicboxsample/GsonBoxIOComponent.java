package osp.leobert.android.magicboxsample;

import android.support.annotation.NonNull;

import osp.leobert.android.magicbox.io.BoxIOComponent;
import osp.leobert.android.magicbox.io.BoxReader;
import osp.leobert.android.magicbox.io.BoxWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> GsonBoxIOComponent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public class GsonBoxIOComponent implements BoxIOComponent {
    @NonNull
    @Override
    public BoxWriter getBoxWriter() {
        return CustomBoxWriter.getInstance();
    }

    @NonNull
    @Override
    public BoxReader getBoxReader() {
        return CustomBoxReader.getInstance();
    }
}
