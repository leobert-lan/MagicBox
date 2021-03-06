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
import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.runner.AndroidJUnit4;
import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.model.VisitCard;
import osp.leobert.android.magicbox.type.Type;

/**
 * <p><b>Package:</b> osp.leobert.android.magicbox </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> MagicBoxTest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/4.
 */
@RunWith(AndroidJUnit4.class)
public class MagicBoxTest {

    private static class TestPar implements Parcelable {
        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(i);
        }
    }

    private static class TestClz {

        @KeepState
        boolean testBool = true;

        @KeepState
        Boolean testNullBoxBool = true;

        @KeepState
        byte testByte1;

        @KeepState
        Byte testByte2;


        @KeepState
        String testString = "this is a test string";

        @KeepState
        String testNullString = null;

        @KeepState(type = Type.BooleanArray)
        boolean[] booleans1;

        @KeepState
        Boolean[] booleans2;

        @KeepState(type = Type.Boolean)
        Boolean[] booleans3;//an error type given

        @KeepState(type = Type.Object)
        Object custom;

        @KeepState
        ArrayList<CharSequence> charSequences;

        @KeepState
        List<CharSequence> charSequences2;

        @KeepState
        ArrayList<Integer> integers;

        @KeepState
        ArrayList<String> strings;

        @KeepState
        ArrayList<Parcelable> parcelables;

        @KeepState
        SparseArray<TestPar> parSparseArray;


        public void foo() {
            testBool = false;
            testNullBoxBool = null;
            testByte1 = 'a';

            booleans1 = new boolean[2];
            booleans1[0] = true;
            booleans1[1] = false;

            booleans2 = new Boolean[2];
            booleans2[0] = null;
            booleans2[1] = true;

            charSequences = new ArrayList<>();
            charSequences.add("hahaha");

            charSequences2 = new ArrayList<>();
            charSequences2.add("test");

            integers = new ArrayList<>();
            integers.add(null);
            integers.add(2);

            strings = new ArrayList<>();
            strings.add(null);
            strings.add("foo");

            parcelables = new ArrayList<>();
            TestPar testPar = new TestPar();
            testPar.setI(10);
            parcelables.add(null);
            parcelables.add(testPar);


            parSparseArray = new SparseArray<>();
            parSparseArray.append(0,null);
            parSparseArray.append(1,testPar);


        }
    }

    MagicBox magicBox;
    TestClz test1;
    TestClz test2;
    Bundle bundle;

    @Before
    public void setUp() throws Exception {
        magicBox = MagicBox.getInstance();
        magicBox.setLogEnable(true);
        bundle = new Bundle();
        test1 = new TestClz();
        test1.foo();

        test2 = new TestClz();

        saveInstanceState();
    }

    //    @Test
    public void saveInstanceState() throws Exception {
        magicBox.saveInstanceState(VisitCard.make(test1), bundle);
    }

    @Test
    public void restoreInstanceState() throws Exception {
        magicBox.restoreInstanceState(VisitCard.make(test2), bundle);
        System.out.println(test2);
    }


}