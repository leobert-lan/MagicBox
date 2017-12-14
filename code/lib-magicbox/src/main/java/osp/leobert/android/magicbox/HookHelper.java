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

import android.app.Instrumentation;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> HookHelper </p>
 * <p><b>Description:</b> helper for hooking Instrumentation </p>
 * Created by leobert on 2017/12/11.
 */

class HookHelper {

    public static final String NAME_ACTIVITY_THREAD = "android.app.ActivityThread";

    private static final String METHOD_CURRENT_ACTIVITY_THREAD = "currentActivityThread";

    private static final String FIELD_MINSTRUMATATION = "mInstrumentation";

    public static Object getCurrentActivityThreadObject() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        Class<?> activityThreadClz = Class.forName(NAME_ACTIVITY_THREAD);
        Method currentActivityThread = activityThreadClz.getDeclaredMethod(METHOD_CURRENT_ACTIVITY_THREAD);
        currentActivityThread.setAccessible(true);

        return currentActivityThread.invoke(null);
    }

    //unused
    public static Instrumentation getOriginalInstrumentation(@NonNull Class<?> activityThreadClz,
                                                             @NonNull Object activityThread)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Field field_instrumentation = activityThreadClz.getDeclaredField(FIELD_MINSTRUMATATION);
        field_instrumentation.setAccessible(true);
        return (Instrumentation) field_instrumentation.get(activityThread);
    }

    public static Field getOriginalInstrumentationField(@NonNull Class<?> activityThreadClz)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Field field_instrumentation = activityThreadClz.getDeclaredField(FIELD_MINSTRUMATATION);
        field_instrumentation.setAccessible(true);
        return field_instrumentation;
    }


    public static void hookInstrumentation(@NonNull Object owner, @NonNull Field instrumentationField,
                                           @NonNull Instrumentation proxy)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException {

        instrumentationField.setAccessible(true);
        instrumentationField.set(owner, proxy);

        MagicBox.getLogger().debug("", "hook ActivityThread#mInstrumentation success");
    }

}
