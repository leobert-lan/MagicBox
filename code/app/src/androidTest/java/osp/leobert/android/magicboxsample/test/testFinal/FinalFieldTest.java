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

package osp.leobert.android.magicboxsample.test.testFinal;

import android.os.Bundle;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import osp.leobert.android.magicbox.MagicBox;
import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.VisitCard;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestObjectTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/5.
 */
@RunWith(AndroidJUnit4.class)
public class FinalFieldTest {


    MagicBox magicBox;

    static class Bar {

        @KeepState // it will be saved and restored. so with caution
        final int i;

        @KeepState
        String ss;

        public Bar(int i) {
            this.i = i;
        }

        void setup() {
            ss = "one string";
        }
    }

    static class Foo {
        @KeepState //meaningless
        final int i = 1;

        @KeepState
        String ss;


        void setup() {
            ss = "one string";
        }
    }


    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        MagicBox.setLogEnable(true);
    }

    @Test
    public void case1() {
        Bar foo1;
        Bar foo2;

        Bundle bundle = new Bundle();
        int i = 0;

        foo1 = new Bar(++i);
        foo1.setup();

        magicBox.saveInstanceState(VisitCard.make(foo1), bundle);
        foo2 = new Bar(++i);
        magicBox.restoreInstanceState(VisitCard.make(foo2), bundle);
        System.out.println(foo2);
        Assert.assertTrue(true);
    }


    @Test
    public void case2() {
        Foo foo1;
        Foo foo2;

        Bundle bundle = new Bundle();

        foo1 = new Foo();
        foo1.setup();

        magicBox.saveInstanceState(VisitCard.make(foo1), bundle);
        foo2 = new Foo();
        magicBox.restoreInstanceState(VisitCard.make(foo2), bundle);
        System.out.println(foo2);
        Assert.assertTrue(true);
    }

}