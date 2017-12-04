package osp.leobert.android.magicbox.model;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.MException;
import osp.leobert.android.magicbox.operators.BundleReader;
import osp.leobert.android.magicbox.operators.BundleWriter;
import osp.leobert.android.magicbox.type.Type;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.model </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> StateField </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class StateField {
    private Type type;
    private String bundleKey;
    private String propertyName;
    private Field field;

    public StateField() {
    }

    public StateField(String propertyName, Field field, String bundleKey, Type type) {
        this.type = type;
        this.bundleKey = bundleKey;
        this.propertyName = propertyName;
        this.field = field;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        BundleWriter writer = getType().getBundleWriter();
        // TODO: 2017/12/4  check null
        try {
            writer.write(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }


    public void restore(@NonNull Object object, @NonNull Bundle bundle) {
        BundleReader reader = getType().getBundleReader();
        // TODO: 2017/12/4  check null
        try {
            reader.read(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }
}
