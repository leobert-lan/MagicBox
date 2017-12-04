package osp.leobert.android.magicbox;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import osp.leobert.android.magicbox.model.StateField;

import static osp.leobert.android.magicbox.log.ILogger.logger;

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

    private MagicBox() {
        // single
        secy = Secy.getInstance();
        logger.setDefaultTag("[MagicBox] ");
    }

    public static MagicBox getInstance() {
        if (instance == null)
            instance = new MagicBox();
        return instance;
    }

    public void setLogEnable(boolean enable) {
        logger.showLog(enable);
    }

    public void saveInstanceState(@NonNull Object object, @NonNull Bundle bundle) {
        List<StateField> strategy = secy.fetchStrategy(object);

        for (StateField field:strategy) {
            field.save(object, bundle);
        }
    }

    public void restoreInstanceState(Object object, @NonNull Bundle bundle) {
        List<StateField> strategy = secy.fetchStrategy(object);

        for (StateField field:strategy) {
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
