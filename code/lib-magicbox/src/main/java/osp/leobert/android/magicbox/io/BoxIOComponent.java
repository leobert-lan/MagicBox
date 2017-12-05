package osp.leobert.android.magicbox.io;

import android.support.annotation.NonNull;

import osp.leobert.android.magicbox.io.readers.DefaultReader;
import osp.leobert.android.magicbox.io.writers.DefaultWriter;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> BoxIOComponent </p>
 * <p><b>Description:</b> one pair of writer and reader for magicbox </p>
 * if you want to define a custom one, suggest to define subclass of {@link BaseCustomBoxWriter} and {@link BaseCustomBoxReader}
 * rather than implementations of {@link BoxWriter} and {@link BoxReader}</p>
 * Created by leobert on 2017/12/5.
 */

public interface BoxIOComponent {

    @NonNull
    BoxWriter getBoxWriter();
    @NonNull
    BoxReader getBoxReader();

    class DefaultComponent implements BoxIOComponent {
        public DefaultComponent() {
        }

        @NonNull
        @Override
        public BoxWriter getBoxWriter() {
            return DefaultWriter.getInstance();
        }

        @NonNull
        @Override
        public BoxReader getBoxReader() {
            return DefaultReader.getInstance();
        }
    }
}
