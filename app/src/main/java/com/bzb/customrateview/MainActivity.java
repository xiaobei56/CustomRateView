package com.bzb.customrateview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bzb.customrateview.view.CircleProgressView;
import com.bzb.customrateview.view.CustomRateView;
//import com.hitomi.cslibrary.CrazyShadow;
//import com.hitomi.cslibrary.base.CrazyShadowDirection;

public class MainActivity extends AppCompatActivity {

    private CustomRateView customRateView;
    private CircleProgressView circleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        customRateView = (CustomRateView) findViewById(R.id.crv);
        circleProgressView = (CircleProgressView) findViewById(R.id.circleProgressbar);
        circleProgressView.setProgress(90);
        circleProgressView.setmTxtHint1("测试测试测");
        circleProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
//        CrazyShadow action = new CrazyShadow.Builder()
//                .setContext(this)
//                .setDirection(CrazyShadowDirection.ALL)
//                .setShadowRadius(3)
//                .setCorner(8)
//                .setBackground(Color.parseColor("#96a993"))
//                .setImpl(CrazyShadow.IMPL_DRAW)
//                .action(circleProgressView);​
//        action.show();
    }

    public void action(View view) {
    }
}
