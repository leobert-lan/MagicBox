package osp.leobert.android.magicbox.model;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.type.Types;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.model </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> StateField </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class StateField {
    private Types types;
    private String bundleKey;
    private String propertyName;
    private Field field;

    public StateField() {
    }

    public StateField(String propertyName, Field field, String bundleKey, Types types) {
        this.types = types;
        this.bundleKey = bundleKey;
        this.propertyName = propertyName;
        this.field = field;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
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
}
