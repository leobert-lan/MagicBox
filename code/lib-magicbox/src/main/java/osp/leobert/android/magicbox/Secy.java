package osp.leobert.android.magicbox;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.type.Type;
import osp.leobert.android.magicbox.type.TypeInferUtils;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> Secy </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */

class Secy {
    private static Secy instance = null;

    private Map<String, List<StateField>> strategies;

    private Secy() {
        // single
        strategies = new HashMap<>();
    }

    public static Secy getInstance() {
        if (instance == null)
            instance = new Secy();
        return instance;
    }


    boolean isStrategyExist(@NonNull Object object) {
        return this.isStrategyExist(object.getClass());
    }

    boolean isStrategyExist(@NonNull Class objectClz) {
        return this.isStrategyExist(objectClz.getName());
    }

    private boolean isStrategyExist(@NonNull String objectClzPath) {
        return strategies.containsKey(objectClzPath);
    }

    public List<StateField> fetchStrategy(@NonNull Object obj) {
        return this.fetchStrategy(obj.getClass());
    }

    private List<StateField> fetchStrategy(@NonNull Class objectClz) {
        if (isStrategyExist(objectClz))
            return strategies.get(objectClz.getName());
        return genStrategy(objectClz);
    }


    // TODO: 2017/12/4 change to private
    List<StateField> genStrategy(@NonNull Class objectClz) {
        List<StateField> strategy = new ArrayList<>();

        List<Field> list = Arrays.asList(objectClz.getDeclaredFields());
        for (int i = 0; i < list.size(); i++) {
            Field field = list.get(i);
            if (field.isAnnotationPresent(KeepState.class)) {
                StateField stateField = analyseField(field);

                if (stateField != null)
                    strategy.add(stateField);
            }
        }
        strategies.put(objectClz.getName(),strategy);
        return strategy;
    }

    private StateField analyseField(@NonNull Field field) {
        if (!field.isAnnotationPresent(KeepState.class))
            return null;
        KeepState keepStateNotation = null;
        for (Annotation notation : field.getDeclaredAnnotations()) {
            if (notation.annotationType().equals(KeepState.class)) {
                keepStateNotation = (KeepState) notation;
                break;
            }
        }

        if (keepStateNotation == null)
            throw new MException("check logic,keepStateNotation is not supposed to be null");

        String fieldName = field.getName();
        String bundleKey = "key_" + fieldName;

        Type type = keepStateNotation.type();

        if (type == Type.Infer)
            type = TypeInferUtils.infer(field);

        return new StateField(fieldName, field, bundleKey, type);
    }
}