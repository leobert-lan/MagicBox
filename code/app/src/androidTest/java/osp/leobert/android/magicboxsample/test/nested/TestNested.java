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

package osp.leobert.android.magicboxsample.test.nested;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import osp.leobert.android.magicbox.MagicBox;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test.nested </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestNested </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/14.
 */
@RunWith(AndroidJUnit4.class)
public class TestNested {
    MagicBox magicBox;
    Bar test1;
    Bar test2;
    Bundle bundle;

    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        magicBox.setLogEnable(true);
        bundle = new Bundle();
        test1 = new Bar();
        test1.setFoo("foo");
        test1.setBar("bar");

        test2 = new Bar();

        saveInstanceState();
    }

    //    @Test
    public void saveInstanceState() throws Exception {
        magicBox.saveInstanceState(test1, bundle);
    }

    @Test
    public void restoreInstanceState() throws Exception {
        magicBox.restoreInstanceState(test2, bundle);
        System.out.println(test2);
    }
}
