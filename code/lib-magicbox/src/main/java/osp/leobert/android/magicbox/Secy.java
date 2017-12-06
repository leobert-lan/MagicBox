package osp.leobert.android.magicbox;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.io.BoxIOComponent;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.type.Type;
import osp.leobert.android.magicbox.type.TypeInferUtils;

import static osp.leobert.android.magicbox.log.ILogger.logger;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> Secy </p>
 * <p><b>Description:</b> works as a secy </p>
 * Created by leobert on 2017/12/4.
 */

final class Secy {
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
        strategies.put(objectClz.getName(), strategy);
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
        else if (type == Type.Object) {
            try {
                return analyseCustoms(field, keepStateNotation);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                throw new MException(e.getMessage());
            }
        } else {
            if (type.canBeChecked()) {
                boolean isCorrectType = type.check(field);
                if (!isCorrectType) {
                    logger.error("", "unCorrect Type set for" + fieldName);
                    return new StateField(fieldName, field, bundleKey, Type.Infer);
                }
            } else {
                logger.debug("", "you have notated " + fieldName + " as " + type.name() +
                        ", one cannot be checked, with cautions");
            }
        }

        return new StateField(fieldName, field, bundleKey, type);
    }

    private StateField analyseCustoms(Field field, KeepState keepStateNotation) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String fieldName = field.getName();
        String bundleKey = "key_" + fieldName;
        Class<? extends BoxIOComponent> clz = keepStateNotation.io();
        BoxIOComponent ioComponent = clz.getConstructor().newInstance();

        return new StateField(fieldName, field, bundleKey, ioComponent);
    }
}
