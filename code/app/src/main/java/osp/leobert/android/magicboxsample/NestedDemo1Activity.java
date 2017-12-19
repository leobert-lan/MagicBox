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

package osp.leobert.android.magicboxsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import osp.leobert.android.magicbox.annotations.KeepSuperState;
import osp.leobert.android.magicboxsample.test.nested.ParentActivity;

@KeepSuperState
public class NestedDemo1Activity extends ParentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "onCreate:" + hashCode());
        setContentView(R.layout.activity_sample);
        ((TextView) findViewById(R.id.text)).setText("打开开发者模式，启用：离开后不保留Activity");

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foo();
                Intent intent = new Intent(NestedDemo1Activity.this, TempActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("test", "onRestart:" + hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "onStart:" + hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test", "onStop:" + hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy:" + hashCode());
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "foo is " + getFoo() + "\rfoofoo is " + getFoofoo());
    }

    private void foo() {
        setFoo("foo");
        // foofoo will be ignored at save and restore
        // because PP has not be annotated with KeepSuperState yet.
        setFoofoo("foofoo");

    }
}
