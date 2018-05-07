package com.bzb.customrateview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bzb.customrateview.view.CustomRateView;

public class MainActivity extends AppCompatActivity {

    private CustomRateView customRateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customRateView = (CustomRateView) findViewById(R.id.crv);
    }

    public void action(View view) {
    }
}
