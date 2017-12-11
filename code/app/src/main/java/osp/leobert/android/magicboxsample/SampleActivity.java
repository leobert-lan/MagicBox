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
        setContentView(R.layout.activity_sample);
        ((TextView) findViewById(R.id.text)).setText("开发者模式，离开后不保留Activity");

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
    protected void onResume() {
        super.onResume();
        Log.d("",""+test);
    }
}
