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

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import osp.leobert.android.magicbox.log.ILogger;
import osp.leobert.android.magicbox.log.impl.DefaultLogger;
import osp.leobert.android.magicbox.model.StateField;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBox </p>
 * <p><b>Description:</b> MagicBox, the facade layer </p>
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

    public void globalDelegateMode() {
        secy.hookInstrumentation();
    }


    public static void setLogEnable(boolean enable) {
        logger.showLog(enable);
        logger.showStackTrace(enable);
        logger.showMonitor(enable);
    }

    public void saveInstanceState(@NonNull Object object, @NonNull Bundle bundle) {
        if (object == null) {
            getLogger().error("[saveInstanceState]","cannot save instance state for null");
            return;
        }
        List<StateField> strategy = secy.fetchStrategy(object);

        for (StateField field : strategy) {
            field.save(object, bundle);
        }
    }

    public void restoreInstanceState(@NonNull Object object, @NonNull Bundle bundle) {
        if (object == null) {
            getLogger().error("[restoreInstanceState]","cannot restore instance state for null");
            return;
        }
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
