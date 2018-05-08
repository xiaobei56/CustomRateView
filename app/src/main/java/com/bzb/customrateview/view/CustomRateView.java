package com.bzb.customrateview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bzb.customrateview.R;

import static android.content.ContentValues.TAG;

/**
 * Created by BZB on 2018/5/4.
 * Project: Complex.
 * Package：com.bzb.customrateview.view.
 */
public class CustomRateView extends ViewGroup {
    int defaultColor = 50;
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
    private int valueSize;
    private int unitSize;
    private int unitMargin;
    private int titleMargin;
    private int progressBarWidth;
    private int shadowWidth;

    private int unitPosition;
    private int progressBarValue;
    private int titlePosition;
    private int tvRateHeight;
    private float titleSize;


    private int titleMarginToValue;
    private int unitMarginToValue;

    public CustomRateView(Context context) {
        super(context, null);

    }


    @SuppressLint("ResourceType")
    public CustomRateView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.CRV_def_style);
    }

    @SuppressLint("ResourceType")
    public CustomRateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTitleMarginToValue(20);
        setunitMarginToValue(5);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRateView, defStyleAttr, 0);
        tvRate = new TextView(context);
        tvUnit = new TextView(context);
        tvTitle = new TextView(context);
//        viewBack = inflate(context, R.drawable.custom_rate_view_back, this);

//        addView(viewBack, 3);
//

        rateStr = typedArray.getString(R.styleable.CustomRateView_rateValue);
        unitStr = typedArray.getString(R.styleable.CustomRateView_unit);
        titleStr = typedArray.getString(R.styleable.CustomRateView_title);

//        colors
        valueColor = typedArray.getColor(R.styleable.CustomRateView_valueColor, Color.WHITE);
        unitColor = typedArray.getColor(R.styleable.CustomRateView_unitColor, Color.WHITE);
        titleColor = typedArray.getColor(R.styleable.CustomRateView_titleColor, Color.WHITE);
        backgroundColor = typedArray.getColor(R.styleable.CustomRateView_backgroundColor, Color.YELLOW);
        processBarColor = typedArray.getColor(R.styleable.CustomRateView_progressBarColor, Color.WHITE);
        shadowColor = typedArray.getColor(R.styleable.CustomRateView_shadowColor, Color.GRAY);

//        dimensions
        valueSize = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_valueSize, 26);
        unitSize = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_unitSize, 12);
        unitMargin = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_unitMargin, 10);
        titleMargin = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_titleMargin, 10);
        titleSize = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_titleSize, 16);
        progressBarWidth = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_progressBarWidth, 3);
        shadowWidth = typedArray.getDimensionPixelOffset(R.styleable.CustomRateView_shadowWidth, 3);

//progressBar Value
        progressBarValue = typedArray.getInteger(R.styleable.CustomRateView_progressBarValue, Color.WHITE);
//        position
        unitPosition = typedArray.getInt(R.styleable.CustomRateView_unitPosition, 2);
        titlePosition = typedArray.getInt(R.styleable.CustomRateView_titlePosition, 3);
        typedArray.recycle();
        setBackgroundResource(R.drawable.custom_rate_view_back);
        addView(tvRate, 0);
        addView(tvUnit, 1);
        addView(tvTitle, 2);

        tvRate.setText(rateStr);
        tvRate.setTextColor(Color.RED);
        tvRate.setTextSize(valueSize);

        tvUnit.setText(unitStr);
        tvUnit.setTextColor(Color.RED);
        tvUnit.setTextSize(unitSize);

        tvTitle.setText(titleStr);
        tvTitle.setTextColor(Color.RED);
        tvTitle.setTextSize(titleSize);
        Log.i(TAG, "CustomRateView: rateStr  " + rateStr);
        Log.i(TAG, "CustomRateView: valueColor  " + valueColor);
        Log.i(TAG, "CustomRateView: valueSize  " + valueSize);

        Log.i(TAG, "CustomRateView: unitStr  " + unitStr);
        Log.i(TAG, "CustomRateView: unitColor  " + unitColor);
        Log.i(TAG, "CustomRateView: unitSize  " + unitSize);

        Log.i(TAG, "CustomRateView: titleStr  " + titleStr);
        Log.i(TAG, "CustomRateView: titleColor  " + titleColor);
        Log.i(TAG, "CustomRateView: titleSize  " + titleSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        //记录当前的高度位置


//        View tvRateChild=getChildAt(0);
//        tvRateChild.
        Log.i(TAG, "onLayout: left" + left);
        Log.i(TAG, "onLayout: top" + top);
        Log.i(TAG, "onLayout: right" + right);
        Log.i(TAG, "onLayout: bottom" + bottom);
//        top = top ;
//        left = left ;
//        right = right +;
//        bottom = bottom + 100;
        int curHeight = top;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            Log.i(TAG, "onLayout: child height" + height + " " + i);
            Log.i(TAG, "onLayout: child width" + width + " " + i);
            if (i == 0) {
                child.layout((left + width) / 2, (top + height / 2), left + width, curHeight + height);
//                child.layout((left + right - width) / 2, (top - height / 2), , curHeight + height);
                Log.e(TAG, "onLayout: left " + (left - width) / 2 + " top " + (top + height / 2) + " right " + left + width + " bottom " + bottom);
                tvRateHeight = height;
            } else if (i == 1) {
                child.layout((left + width) / 2 + 10, (top + bottom + tvRateHeight) / 2 - height, left + width, curHeight + height);
            } else if (i == 2) {
                child.layout((left+width) / 2, (top + bottom + tvRateHeight) / 2 + 10, left + width, curHeight + height);
            }
//            curHeight += height;
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

//        Paint paint = new Paint();
//        //开始绘制
//        canvas.drawCircle(centerX, centerY, r, paint);
    }

    public void setTrans(int trans) {
        defaultColor = trans;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int childCount = getChildCount();
//        View child = getChildAt(0);
//        int width = child.getMeasuredWidth() + getChildAt(1).getMeasuredWidth() + getUnitMarginToValue();
//        int height = child.getMeasuredHeight() + getChildAt(2).getMeasuredHeight() + getTitleMarginToValue();
//        setMeasuredDimension(width, height);
//        if (childCount == 0) {//如果没有子View,当前ViewGroup没有存在的意义，不用占用空间
//            setMeasuredDimension(0, 0);
//        } else {
//            //如果宽高都是包裹内容
//            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//                //我们将高度设置为所有子View的高度相加，宽度设为子View中最大的宽度
//                int height = getTotleHeight();
//                int width = getMaxChildWidth();
//                setMeasuredDimension(width, height);
//
//            } else if (heightMode == MeasureSpec.AT_MOST) {//如果只有高度是包裹内容
//                //宽度设置为ViewGroup自己的测量宽度，高度设置为所有子View的高度总和
//                setMeasuredDimension(widthSize, getTotleHeight());
//            } else if (widthMode == MeasureSpec.AT_MOST) {//如果只有宽度是包裹内容
//                //宽度设置为子View中宽度最大的值，高度设置为ViewGroup自己的测量值
//                setMeasuredDimension(getMaxChildWidth(), heightSize);
//
//            }
//        }
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

    public int getTitleMarginToValue() {
        return titleMarginToValue;
    }

    public void setTitleMarginToValue(int titleMarginToValue) {
        this.titleMarginToValue = titleMarginToValue;
    }

    public void setunitMarginToValue(int unitMarginToValue) {
        this.unitMarginToValue = unitMarginToValue;
    }

    public int getUnitMarginToValue() {
        return unitMarginToValue;
    }
}
