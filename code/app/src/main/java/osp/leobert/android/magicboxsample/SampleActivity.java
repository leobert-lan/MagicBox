package osp.leobert.android.magicboxsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import osp.leobert.android.magicbox.annotations.KeepState;

public class SampleActivity extends AppCompatActivity {

    @KeepState
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test","onCreate:"+hashCode());
        setContentView(R.layout.activity_sample);
        ((TextView) findViewById(R.id.text)).setText("打开开发者模式，启用：离开后不保留Activity");

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test = "this is a string";

                Intent intent = new Intent(SampleActivity.this, TempActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("test","onRestart:"+hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart:"+hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test","onStop:"+hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test","onDestroy:"+hashCode());
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","test is "+test);
    }
}
