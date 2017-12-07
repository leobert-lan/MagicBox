package osp.leobert.android.magicbox;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.List;

import osp.leobert.android.magicbox.log.ILogger;
import osp.leobert.android.magicbox.log.impl.DefaultLogger;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.proxy.MagicBoxInstrumentation;
import osp.leobert.android.magicbox.proxy.hook.HookHelper;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBox </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class MagicBox {
    private static MagicBox instance = null;

    private Secy secy;

    private static final ILogger logger = new DefaultLogger("[MagicBox] ");

    public static ILogger getLogger() {
        return logger;
    }

    private MagicBox() {
        // single
        secy = Secy.getInstance();
    }

    public static MagicBox getInstance() {
        if (instance == null)
            instance = new MagicBox();
        return instance;
    }

    @SuppressLint("PrivateApi")
    public static void globalDelegateMode() {
        final String msg_fail = "cannot enable global delegate mode";
        try {
            Class<?> activityThreadClz = Class.forName(HookHelper.NAME_ACTIVITY_THREAD);
            Object owner = HookHelper.getCurrentActivityThreadObject();
            Instrumentation mBase = HookHelper.getOriginalInstrumentation(activityThreadClz, owner);
            Field field = HookHelper.getOriginalInstrumentationField(activityThreadClz);
//            Instrumentation proxy = MagicBoxInstrumentationProxy.create(mBase);

            Instrumentation proxy = new MagicBoxInstrumentation(mBase);

//            if (owner == null || field == null||proxy == null)  ignore check

            HookHelper.hookInstrumentation(owner, field, proxy);

        } catch (Exception e) {
            e.printStackTrace();
            getLogger().error("[globalDelegateMode]", msg_fail);
        }
    }

    public static void setLogEnable(boolean enable) {
        logger.showLog(enable);
        logger.showStackTrace(enable);
        logger.showMonitor(enable);
    }

    public void saveInstanceState(@NonNull Object object, @NonNull Bundle bundle) {
        List<StateField> strategy = secy.fetchStrategy(object);

        for (StateField field : strategy) {
            field.save(object, bundle);
        }
    }

    public void restoreInstanceState(Object object, @NonNull Bundle bundle) {
        List<StateField> strategy = secy.fetchStrategy(object);

        for (StateField field : strategy) {
            field.restore(object, bundle);
        }
    }

    public boolean isStrategyExist(@NonNull Object object) {
        return secy.isStrategyExist(object);
    }

    boolean isStrategyExist(@NonNull Class objectClz) {
        return secy.isStrategyExist(objectClz);
    }
}
