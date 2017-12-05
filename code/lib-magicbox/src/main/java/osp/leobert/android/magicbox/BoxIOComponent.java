package osp.leobert.android.magicbox;

import android.support.annotation.NonNull;

import osp.leobert.android.magicbox.operators.BundleReader;
import osp.leobert.android.magicbox.operators.BundleWriter;
import osp.leobert.android.magicbox.operators.readers.DefaultReader;
import osp.leobert.android.magicbox.operators.writers.DefaultWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BoxIOComponent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */

public interface BoxIOComponent {

    @NonNull BundleWriter getBundleWriter();
    @NonNull BundleReader getBundleReader();

    class DefaultComponent implements BoxIOComponent {
        public DefaultComponent() {
        }

        @NonNull
        @Override
        public BundleWriter getBundleWriter() {
            return DefaultWriter.getInstance();
        }

        @NonNull
        @Override
        public BundleReader getBundleReader() {
            return DefaultReader.getInstance();
        }
    }
}
