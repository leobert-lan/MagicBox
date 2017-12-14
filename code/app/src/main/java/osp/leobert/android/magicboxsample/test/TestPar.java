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

package osp.leobert.android.magicboxsample.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p><b>Package:</b> osp.leobert.android.magicboxsample.test </p>
 * <p><b>Project:</b> code </p>
 * <p><b>Classname:</b> TestPar </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/12/14.
 */

public class TestPar implements Parcelable {
    int i;

    public TestPar() {
    }

    protected TestPar(Parcel in) {
        i = in.readInt();
    }

    public static final Creator<TestPar> CREATOR = new Creator<TestPar>() {
        @Override
        public TestPar createFromParcel(Parcel in) {
            return new TestPar(in);
        }

        @Override
        public TestPar[] newArray(int size) {
            return new TestPar[size];
        }
    };

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

