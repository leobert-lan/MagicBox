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

import android.support.annotation.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import osp.leobert.android.magicbox.MException;
import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.annotations.BeanFactory;
import osp.leobert.android.magicbox.di.Factory;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox.model </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> VisitCard </p>
 * <p><b>Description:</b> access object of MagicBox </p>
 * Created by leobert on 2017/12/19.
 */

public class VisitCard<T> {
    private final Class<T> address;

    private T oneSelf;

    private Class<? extends Factory> factoryClass;

    public VisitCard(Class<T> address) {
        this.address = address;
        initFactory();
    }

    @SuppressWarnings("unchecked")
    private void initFactory() {
        if (!hasFactory())
            return;

        BeanFactory beanFactoryNotation = address.getAnnotation(BeanFactory.class);
        if (beanFactoryNotation == null)
            return;

        factoryClass = beanFactoryNotation.factory();
    }

    private void setOneSelf(T oneSelf) {
        this.oneSelf = oneSelf;
    }

    public static <T> VisitCard<T> make(@NonNull Class<T> address, T oneSelf) {
        VisitCard<T> card = new VisitCard<>(address);
        card.setOneSelf(oneSelf);
        return card;
    }

    @SuppressWarnings("unchecked")
    public static <T> VisitCard<T> make(@NonNull T oneSelf) {
        if (oneSelf == null) {
            final String msg = "cannot make VisitCard for a null object";
            MagicBox.getLogger().error("", msg);
            throw new MException(msg);
        }

        return make((Class<T>) oneSelf.getClass(), oneSelf);
    }


    public Class<T> getAddress() {
        return address;
    }

    public T getOneSelf() {
        return oneSelf;
    }

    public Factory getBeanFactory() { // ignore rawtype
        if (!hasFactory() || factoryClass == null)
            return Factory.NullFactory.getInstance();

        try {
            Constructor constructor = factoryClass.getConstructor();
            constructor.setAccessible(true);
            return (Factory) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return Factory.NullFactory.getInstance();
        }

    }

    private boolean hasFactory() {
        return address.isAnnotationPresent(BeanFactory.class);
    }
}
