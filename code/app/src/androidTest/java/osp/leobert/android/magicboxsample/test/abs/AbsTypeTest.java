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

package osp.leobert.android.magicboxsample.test.abs;

import android.os.Bundle;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.model.VisitCard;
import osp.leobert.android.magicboxsample.test.abs.case1.Bar;
import osp.leobert.android.magicboxsample.test.abs.case1.Foo;
import osp.leobert.android.magicboxsample.test.abs.case2.Bar2;
import osp.leobert.android.magicboxsample.test.abs.case2.Foo2;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestObjectTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@RunWith(AndroidJUnit4.class)
public class AbsTypeTest {


    MagicBox magicBox;


    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        MagicBox.setLogEnable(true);
    }

    @Test
    public void case1() {
        Foo foo1;
        Foo foo2;

        Bundle bundle = new Bundle();

        foo1 = new Bar();
        foo1.setup();

        magicBox.saveInstanceState(VisitCard.make(foo1), bundle);
        foo2 = new Bar();
        magicBox.restoreInstanceState(VisitCard.make(foo2), bundle);
        System.out.println(foo2);
        Assert.assertTrue(true);
    }

    @Test
    public void case2() {
        Foo2 foo1;
        Foo2 foo2;

        Bundle bundle = new Bundle();

        foo1 = new Bar2();
        foo1.setup();

        magicBox.saveInstanceState(VisitCard.make(foo1), bundle);
        foo2 = new Bar2();
        magicBox.restoreInstanceState(VisitCard.make(foo2), bundle);
        System.out.println(foo2);
        Assert.assertTrue(true);
    }

}