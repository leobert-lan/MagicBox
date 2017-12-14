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

package osp.leobert.android.magicbox.type.cluster;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import osp.leobert.android.magicbox.type.GenericUtils;
import osp.leobert.android.magicbox.type.SupposeType;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.type.cluster </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> GenericCollection </p>
 * <p><b>Description:</b> GenericCollection,like: ArrayList&lt;T&gt; ; Map&lt;Foo,Bar&gt;</p>
 * Created by leobert on 2017/12/6.
 */

abstract class GenericCollection implements SupposeType {
    private final Class<?> collectionType;

    private final Class<?>[] genericTypes;

    GenericCollection(@NonNull Class<?> collectionType, @NonNull Class<?>[] genericTypes) {
        this.collectionType = collectionType;
        this.genericTypes = genericTypes;
    }

    @Override
    public final boolean canBeChecked() {
        return true;
    }

    @Override
    public final boolean check(Field field) {
        if (field == null)
            return false;

        Type type = field.getGenericType();

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();

            if (rawType.equals(collectionType)) {
                return checkGenericType(parameterizedType);
            } else
                return false;
        }
        return false;
    }

    private boolean checkGenericType(ParameterizedType type) {
        int size = type.getActualTypeArguments().length;
        if (size != genericTypes.length)
            return false;
        for (int i = 0; i < size; i++) {
            Class<?> genericClass = GenericUtils.getGenericClass(type, i);
            if (!supposeTo(genericTypes[i], genericClass))
                return false;
        }
        return true;
    }

    protected abstract boolean supposeTo(Class<?> suppose, Class<?> toBeCheck);
}
