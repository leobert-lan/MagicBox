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

package osp.leobert.android.magicbox.io;

import android.os.Bundle;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.model.StateField;
import osp.leobert.android.magicbox.model.VisitCard;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.io </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> DelegateBoxWriter </p>
 * <p><b>Description:</b> which can inject into the field and rewrite the state for it </p>
 * Created by leobert on 2017/12/19.
 */

public class DelegateBoxWriter implements BoxWriter {
    private static DelegateBoxWriter instance = null;

    private DelegateBoxWriter() {
        // single
    }

    public static DelegateBoxWriter getInstance() {
        if (instance == null)
            instance = new DelegateBoxWriter();
        return instance;
    }

    @Override
    public void write(Bundle bundle, Object to, StateField field) throws IllegalAccessException {
        Field propertyField = field.getField();
        propertyField.setAccessible(true);
        Object delegate = propertyField.get(to);

        VisitCard card;
        if (delegate == null) {
//            MagicBox.getLogger().monitor("ignore save delegate for null object");
//            return;
            card = VisitCard.make(propertyField.getType(), null);
        } else
            card = VisitCard.make(delegate);

        MagicBox.getInstance().saveInstanceState(card, bundle);

    }
}
