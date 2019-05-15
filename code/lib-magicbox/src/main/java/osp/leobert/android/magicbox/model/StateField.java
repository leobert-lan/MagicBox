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

package osp.leobert.android.magicbox.model;

import android.os.Bundle;
import androidx.annotation.NonNull;

import java.lang.reflect.Field;

import osp.leobert.android.magicbox.MException;
import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.di.Factory;
import osp.leobert.android.magicbox.io.BoxIOComponent;
import osp.leobert.android.magicbox.io.BoxReader;
import osp.leobert.android.magicbox.io.BoxWriter;


/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.model </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> StateField </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/1.
 */

public class StateField {
    private String propertyName;
    private Field field;
    private String bundleKey;
    private BoxIOComponent ioComponent;

    public StateField() {
    }

    public StateField(String propertyName, Field field, String bundleKey, BoxIOComponent ioComponent) {
        this.ioComponent = ioComponent;
        this.bundleKey = bundleKey;
        this.propertyName = propertyName;
        this.field = field;
    }

    public BoxIOComponent getIoComponent() {
        return ioComponent;
    }

    public void setIoComponent(BoxIOComponent ioComponent) {
        this.ioComponent = ioComponent;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void save(@NonNull Object object, @NonNull Bundle bundle) {
        BoxWriter writer = getIoComponent().getBoxWriter();
        if (object == null) { //runtime check
            MagicBox.getLogger().error("StateField#save", "ignore save anything of one null target");
            return;
        }
        try {
            writer.write(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }


    public void restore(@NonNull Object object, @NonNull Bundle bundle, @NonNull Factory beanFactory) {
        BoxReader reader = getIoComponent().getBoxReader();
//        if (object == null && reader.preHandleNull()) { //runtime check
//            object = beanFactory.getBean();
//        }

        if (object == null) { //runtime check
            MagicBox.getLogger().error("StateField#restore", "ignore restore anything of one null target");
            return;
        }


        try {
            reader.read(bundle, object, this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new MException(e.getCause());
        }
    }
}
