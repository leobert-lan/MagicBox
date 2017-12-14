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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.StateField;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> SecyTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */
public class SecyTest {

    private static class TestClz {
        @KeepState
        boolean testBool = true;

        @KeepState
        Boolean testNullBoxBool;

        @KeepState
        String string = "";

        @KeepState
        boolean[] booleans = new boolean[2];

        @KeepState
        Boolean[] booleans2 = new Boolean[2]; //cannot check

    }

    Secy secy;
    TestClz testClz;

    @Before
    public void setUp() throws Exception {
        secy = Secy.getInstance();
        testClz = new TestClz();
    }

    @Test
    public void genStrategy() throws Exception {
        List<StateField> stateFields = secy.genStrategy(testClz.getClass());
        System.out.print("end");
        Assert.assertTrue(true);
    }

}