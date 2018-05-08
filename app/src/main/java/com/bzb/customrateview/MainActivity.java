package com.bzb.customrateview;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //获取样式中的属性值
////            TypedValue typedValue = new TypedValue();
////            this.getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
////            int[] attribute = new int[] { android.R.attr.colorPrimary };
////            TypedArray array = this.obtainStyledAttributes(typedValue.resourceId, attribute);
////            int color = array.getColor(0, getResources().getColor(R.color.trans1));
////            array.recycle();
//
//            window.setStatusBarColor(getResources().getColor(R.color.trans1));
//        }
        setContentView(R.layout.activity_main);
        StatusBarUtils.setImage(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            local LayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//        customRateView = (CustomRateView) findViewById(R.id.crv);
        circleProgressView = (CircleProgressView) findViewById(R.id.circleProgressbar);
        circleProgressView.setProgress(86);
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
