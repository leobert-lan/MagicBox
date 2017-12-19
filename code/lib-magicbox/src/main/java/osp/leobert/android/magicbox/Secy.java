/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package osp.leobert.android.magicbox;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
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
import osp.leobert.android.magicbox.annotations.KeepSuperState;
import osp.leobert.android.magicbox.fake.MagicBoxInstrumentation;
import osp.leobert.android.magicbox.io.BoxIOComponent;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.type.Type;
import osp.leobert.android.magicbox.type.TypeInferUtils;


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

    @SuppressWarnings("unchecked")
    private boolean needKeepSuper(@NonNull Class objectClz) {
        KeepSuperState keepSuperStateNotation =
                (KeepSuperState) objectClz.getAnnotation(KeepSuperState.class);

        return keepSuperStateNotation != null;
    }


    List<StateField> genStrategy(@NonNull Class objectClz) {
        Class temp = objectClz;
        List<StateField> strategy = new ArrayList<>();

        while (true) {
            List<Field> list = Arrays.asList(temp.getDeclaredFields());
            for (int i = 0; i < list.size(); i++) {
                Field field = list.get(i);
                if (field.isAnnotationPresent(KeepState.class)) {
                    StateField stateField = analyseField(field, temp.getSimpleName());

                    if (stateField != null)
                        strategy.add(stateField);
                }
            }

            if (!needKeepSuper(temp)) break;
            temp = temp.getSuperclass();
        }


        strategies.put(objectClz.getName(), strategy);
        return strategy;
    }

    private StateField analyseField(@NonNull Field field, String tag) {
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
        String bundleKey = "key_" + tag + "#" + fieldName;

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
                    MagicBox.getLogger().error("", "unCorrect Type set for" + fieldName);
                    return new StateField(fieldName, field, bundleKey, Type.Infer);
                }
            } else {
                MagicBox.getLogger().debug("", "you have notated " + fieldName + " as " + type.name() +
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

    @SuppressLint("PrivateApi")
    public void hookInstrumentation() {

        final String msg_fail = "cannot enable global delegate mode";
        try {
            Class<?> activityThreadClz = Class.forName(HookHelper.NAME_ACTIVITY_THREAD);
            Object owner = HookHelper.getCurrentActivityThreadObject();
//            Instrumentation mBase = HookHelper.getOriginalInstrumentation(activityThreadClz, owner);
            Field field = HookHelper.getOriginalInstrumentationField(activityThreadClz);

//            Instrumentation proxy = InstrumentationProxy.create(application);
            Instrumentation proxy = new MagicBoxInstrumentation();
//            if (owner == null || field == null||proxy == null)  ignore check

            HookHelper.hookInstrumentation(owner, field, proxy);

        } catch (Exception e) {
            e.printStackTrace();
            MagicBox.getLogger().error("[globalDelegateMode]", msg_fail);
        }
    }
}
