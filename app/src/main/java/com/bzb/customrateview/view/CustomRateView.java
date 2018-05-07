package com.bzb.customrateview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bzb.customrateview.R;

import java.util.zip.Inflater;

/**
 * Created by BZB on 2018/5/4.
 * Project: Complex.
 * Package：com.bzb.customrateview.view.
 */
public class CustomRateView extends ViewGroup {
    private TextView tvRate;
    private TextView tvUnit;
    private TextView tvTitle;
    private View viewBack;
    private TypedArray typedArray;

//    color值
    private int valueColor;
    private int unitColor;
    private int titleColor;
    private int backgroundColor;
    private int processBarColor;
    private int shadowColor;
//    rate值 title 单位
    private String rateStr;
    private String unitStr;
    private String titleStr;
    private float valueSize;
    private float unitSize;
    private float unitMargin;
    private float titleMargin;
    private float progressBarWidth;
    private float shadowWidth;

    public CustomRateView(Context context) {
        super(context,null);

    }

    @SuppressLint("ResourceType")
    public CustomRateView(Context context, AttributeSet attrs) {
        this(context, attrs,R.attr.CRV_def_style);
    }

    @SuppressLint("ResourceType")
    public CustomRateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRateView,defStyleAttr,0);
        tvRate = new TextView(context);
        tvUnit = new TextView(context);
        tvTitle = new TextView(context);
//        viewBack = inflate(context, R.drawable.custom_rate_view_back, this);
        setBackgroundResource(R.drawable.custom_rate_view_shadow);
        addView(tvRate, 0);
        addView(tvUnit, 1);
        addView(tvTitle, 2);
//        addView(viewBack, 3);
//

        rateStr = typedArray.getString(R.styleable.CustomRateView_rateValue);
        unitStr = typedArray.getString(R.styleable.CustomRateView_unit);
        titleStr = typedArray.getString(R.styleable.CustomRateView_title);

//        colors
        valueColor = typedArray.getColor(R.styleable.CustomRateView_valueColor,defStyleAttr);
        unitColor = typedArray.getColor(R.styleable.CustomRateView_unitColor,defStyleAttr);
        titleColor = typedArray.getColor(R.styleable.CustomRateView_titleColor,defStyleAttr);
        backgroundColor = typedArray.getColor(R.styleable.CustomRateView_backgroundColor,defStyleAttr);
        processBarColor = typedArray.getColor(R.styleable.CustomRateView_progressBarColor,defStyleAttr);
        shadowColor = typedArray.getColor(R.styleable.CustomRateView_shadowColor,defStyleAttr);

//        dimensions
        valueSize = typedArray.getDimension(R.styleable.CustomRateView_valueSize,defStyleAttr);
        unitSize = typedArray.getDimension(R.styleable.CustomRateView_unitSize,defStyleAttr);
        unitMargin = typedArray.getDimension(R.styleable.CustomRateView_unitMargin,defStyleAttr);
        titleMargin = typedArray.getDimension(R.styleable.CustomRateView_titleMargin,defStyleAttr);
        progressBarWidth = typedArray.getDimension(R.styleable.CustomRateView_progressBarWidth,defStyleAttr);
        shadowWidth = typedArray.getDimension(R.styleable.CustomRateView_shadowWidth,defStyleAttr);



        tvRate.setText(rateStr);
        tvRate.setTextColor(valueColor);

        tvUnit.setText(unitStr);
        tvUnit.setTextColor(unitColor);

        tvTitle.setText(titleStr);
        tvTitle.setTextColor(titleColor);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        //记录当前的高度位置
        int curHeight = top;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            child.layout(left, curHeight, left + width, curHeight + height);
            curHeight += height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop() + r;

        Paint paint = new Paint();
        paint.setShadowLayer(1,5,5,Color.RED);
        paint.setAlpha(defaultColor);
        //开始绘制
        canvas.drawCircle(centerX, centerY, r, paint);
    }

    int defaultColor = 50;

    public void setTrans(int trans) {
        defaultColor = trans;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();

        if (childCount == 0) {//如果没有子View,当前ViewGroup没有存在的意义，不用占用空间
            setMeasuredDimension(0, 0);
        } else {
            //如果宽高都是包裹内容
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //我们将高度设置为所有子View的高度相加，宽度设为子View中最大的宽度
                int height = getTotleHeight();
                int width = getMaxChildWidth();
                setMeasuredDimension(width, height);

            } else if (heightMode == MeasureSpec.AT_MOST) {//如果只有高度是包裹内容
                //宽度设置为ViewGroup自己的测量宽度，高度设置为所有子View的高度总和
                setMeasuredDimension(widthSize, getTotleHeight());
            } else if (widthMode == MeasureSpec.AT_MOST) {//如果只有宽度是包裹内容
                //宽度设置为子View中宽度最大的值，高度设置为ViewGroup自己的测量值
                setMeasuredDimension(getMaxChildWidth(), heightSize);

            }
        }
    }

    /***
     * 获取子View中宽度最大的值
     */
    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth)
                maxWidth = childView.getMeasuredWidth();

        }

        return maxWidth;
    }

    /***
     * 将所有子View的高度相加
     **/
    private int getTotleHeight() {
        int childCount = getChildCount();
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();

        }

        return height;
    }

    /**
     * 设置单位
     *
     * @param unit
     */
    public void initRateUnit(String unit) {

    }

    /**
     * 设置比率值
     *
     * @param value
     */
    public void initRateValue(String value) {

    }

    /**
     * 设置背影
     *
     * @param view
     */
    public void initBack(View view) {

    }

    /**
     * 设置比率环
     *
     * @param view
     */
    public void initCircleRate(View view) {

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {

    }

    /**
     * 更新数据
     *
     * @param value
     */
    public void updateData(String value) {

    }
}
