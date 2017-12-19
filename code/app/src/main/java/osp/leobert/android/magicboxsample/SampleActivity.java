package osp.leobert.android.magicboxsample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import osp.leobert.android.magicbox.annotations.KeepState;
import osp.leobert.android.magicbox.annotations.KeepSuperState;
import osp.leobert.android.magicbox.type.Type;
import osp.leobert.android.magicboxsample.test.TestPar;
import osp.leobert.android.magicboxsample.test.nested.ParentActivity;

@KeepSuperState
public class SampleActivity extends AppCompatActivity {

    @KeepState
    String test;

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
        parSparseArray.append(0, null);
        parSparseArray.append(1, testPar);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "onCreate:" + hashCode());
        setContentView(R.layout.activity_sample);
        ((TextView) findViewById(R.id.text)).setText("打开开发者模式，启用：离开后不保留Activity");

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test = "this is a string";
                foo();

                Intent intent = new Intent(SampleActivity.this, TempActivity.class);
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
        Log.d("test", "test is " + test);
    }
}
