package osp.leobert.android.magicbox.proxy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.PersistableBundle;

import java.lang.reflect.Method;

import osp.leobert.android.magicbox.MagicBox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.proxy </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBoxInstrumentation </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/7.
 */

public class MagicBoxInstrumentation extends Instrumentation {

    private final Instrumentation mBase;

    private static final String METHOD_NAME_CALL_ACTIVITY_ON_RESTORE_INSTANCE_STATE =
            "callActivityOnRestoreInstanceState";

    private Method callActivityOnRestoreInstanceState1;

    private Method callActivityOnRestoreInstanceState2;


    private static final String METHOD_NAME_CALL_ACTIVITY_ON_SAVE_INSTANCE_STATE =
            "callActivityOnSaveInstanceState";
    private Method callActivityOnSaveInstanceState1;

    private Method callActivityOnSaveInstanceState2;


    public MagicBoxInstrumentation(Instrumentation mBase) {
        this.mBase = mBase;

        callActivityOnRestoreInstanceState1 = getAccessibleMethod(METHOD_NAME_CALL_ACTIVITY_ON_RESTORE_INSTANCE_STATE,
                Activity.class, Bundle.class);
        callActivityOnRestoreInstanceState2 = getAccessibleMethod(METHOD_NAME_CALL_ACTIVITY_ON_RESTORE_INSTANCE_STATE,
                Activity.class, Bundle.class, PersistableBundle.class);

        callActivityOnSaveInstanceState1 = getAccessibleMethod(METHOD_NAME_CALL_ACTIVITY_ON_SAVE_INSTANCE_STATE,
                Activity.class, Bundle.class);
        callActivityOnSaveInstanceState1 = getAccessibleMethod(METHOD_NAME_CALL_ACTIVITY_ON_SAVE_INSTANCE_STATE,
                Activity.class, Bundle.class, PersistableBundle.class);
    }

    @SuppressLint("PrivateApi")
    private static Method getAccessibleMethod(String name, Class<?>... parameterTypes) {
        try {
            Method method = Instrumentation.class.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    {
//        try {
//            AppLogUtils.e("my instrumentation start activity");
//            // 查找当前方法
//            Method execStartActivityMethod = null;
//            try {
//                execStartActivityMethod = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            ActivityResult result = null;
//            if (execStartActivityMethod != null) {
//                execStartActivityMethod.setAccessible(true);
//                try {
//                    // 执行activity启动
//                    result = (ActivityResult) execStartActivityMethod.invoke(mOriginInstrumentation, who, contextThread, token, target, intent, requestCode, options);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//            return result;
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState);
        MagicBox.getLogger().monitor("callActivityOnRestoreInstanceState1");
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState, PersistableBundle persistentState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
        MagicBox.getLogger().monitor("callActivityOnRestoreInstanceState2");
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        super.callActivityOnSaveInstanceState(activity, outState);
        MagicBox.getLogger().monitor("callActivityOnSaveInstanceState1");
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState, PersistableBundle outPersistentState) {
        super.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
        MagicBox.getLogger().monitor("callActivityOnSaveInstanceState2");
    }
}
