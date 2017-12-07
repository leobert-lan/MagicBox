package osp.leobert.android.magicboxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import osp.leobert.android.magicbox.annotations.KeepState;

public class SampleActivity extends AppCompatActivity {

    @KeepState
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }
}
