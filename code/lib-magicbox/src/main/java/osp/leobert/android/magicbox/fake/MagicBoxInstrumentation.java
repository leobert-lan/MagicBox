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

package osp.leobert.android.magicbox.fake;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.PersistableBundle;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.model.VisitCard;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.proxy </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBoxInstrumentation </p>
 * <p><b>Description:</b> MagicBoxInstrumentation </p>
 * Created by leobert on 2017/12/12.
 */

public class MagicBoxInstrumentation extends Instrumentation {

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        MagicBox.getInstance().saveInstanceState(VisitCard.make(activity), outState);
        super.callActivityOnSaveInstanceState(activity, outState);
    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState, PersistableBundle outPersistentState) {
        MagicBox.getInstance().saveInstanceState(VisitCard.make(activity), outState);
        super.callActivityOnSaveInstanceState(activity, outState, outPersistentState);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState);
        MagicBox.getInstance().restoreInstanceState(VisitCard.make(activity), savedInstanceState);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState, PersistableBundle persistentState) {
        super.callActivityOnRestoreInstanceState(activity, savedInstanceState, persistentState);
        MagicBox.getInstance().restoreInstanceState(VisitCard.make(activity), savedInstanceState);
    }
}
