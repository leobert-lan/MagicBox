package osp.leobert.android.magicbox.model;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.BoxIOComponent;
import osp.leobert.android.magicbox.MException;
import osp.leobert.android.magicbox.io.BoxReader;
import osp.leobert.android.magicbox.io.BoxWriter;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.model </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> StateField </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class StateField {
    private String propertyName;
    private Field field;
    private String bundleKey;
    private BoxIOComponent ioComponent;

    public StateField() {
    }

    public StateField(String propertyName, Field field, String bundleKey, BoxIOComponent ioComponent) {
        this.ioComponent = ioComponent;
        this.bundleKey = bundleKey;
        this.propertyName = propertyName;
        this.field = field;
    }

    public BoxIOComponent getIoComponent() {
        return ioComponent;
    }

    public void setIoComponent(BoxIOComponent ioComponent) {
        this.ioComponent = ioComponent;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void save(@NonNull Object object, @NonNull Bundle bundle) {
        BoxWriter writer = getIoComponent().getBoxWriter();
        // TODO: 2017/12/4  check null
        try {
            writer.write(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }


    public void restore(@NonNull Object object, @NonNull Bundle bundle) {
        BoxReader reader = getIoComponent().getBoxReader();
        // TODO: 2017/12/4  check null
        try {
            reader.read(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }
}
