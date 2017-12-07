package osp.leobert.android.magicbox.proxy.hook;

import android.app.Instrumentation;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import osp.leobert.android.magicbox.MagicBox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.proxy.hook </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> HookHelper </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/7.
 */

public class HookHelper {

    public static final String NAME_ACTIVITY_THREAD = "android.app.ActivityThread";

    private static final String METHOD_CURRENT_ACTIVITY_THREAD = "currentActivityThread";

    private static final String FIELD_MINSTRUMATATION = "mInstrumentation";

    public static Object getCurrentActivityThreadObject() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> activityThreadClz = Class.forName(NAME_ACTIVITY_THREAD);
        Method currentActivityThread = activityThreadClz.getDeclaredMethod(METHOD_CURRENT_ACTIVITY_THREAD);
        currentActivityThread.setAccessible(true);

        return currentActivityThread.invoke(null);
    }

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
