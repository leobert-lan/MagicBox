package osp.leobert.android.magicbox.proxy;

import android.app.Application;
import android.app.Instrumentation;
import android.os.Bundle;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.proxy.EnhancerProxy;
import osp.leobert.android.proxy.method.MethodProxy;
import osp.leobert.android.proxy.method.interceptor.MethodInterceptor;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.proxy </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> InstrumentationProxy </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/11.
 */

public class InstrumentationProxy {

    public static Instrumentation create(Application application) {
        EnhancerProxy enhancerProxy = new EnhancerProxy(application);
        enhancerProxy.setSuperclass(Instrumentation.class);
        enhancerProxy.setCallback(Proxy.getInstance());
        return (Instrumentation) enhancerProxy.create();
    }


    private static final class Proxy implements MethodInterceptor {
        private static Proxy instance = null;

        private Proxy() {
            // single
        }

        public static Proxy getInstance() {
            if (instance == null)
                instance = new Proxy();
            return instance;
        }

        private static final String METHOD_NAME_CAOSIS = "callActivityOnSaveInstanceState";

        private static final String METHOD_NAME_CAORIS = "callActivityOnRestoreInstanceState";


        @Override
        public Object intercept(Object object, Object[] args, MethodProxy methodProxy) throws Exception {
            if (METHOD_NAME_CAOSIS.equals(methodProxy.getMethodName())) {
                if (args.length >= 2 && args[1] instanceof Bundle) {
                    Bundle bundle = (Bundle) args[1];
                    MagicBox.getInstance().saveInstanceState(args[0], bundle);
                } else {
                    MagicBox.getLogger().error(METHOD_NAME_CAOSIS, "mismatch!");
                }
            }

            Object obj = methodProxy.invokeSuper(object, args);

            if (METHOD_NAME_CAORIS.equals(methodProxy.getMethodName())) {
                if (args.length >= 2 && args[1] instanceof Bundle) {
                    Bundle bundle = (Bundle) args[1];
                    MagicBox.getInstance().restoreInstanceState(args[0], bundle);
                } else {
                    MagicBox.getLogger().error(METHOD_NAME_CAOSIS, "mismatch!");
                }
            }

            return obj;
        }
    }


}
