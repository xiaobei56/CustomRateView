package com.bzb.customrateview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressView extends View {
    private static final String TAG = "CircleProgressBar";
    private final int mCircleLineStrokeWidth = 8;
    private final int mTxtStrokeWidth = 2;
    // 画圆所在的距形区域
    private final RectF mRectF;
    private final RectF mRectFBack1;
    private final RectF mRectFBack2;
    private final RectF mRectFBack3;
    private final RectF mRectFBack4;
    private final Paint mPaint;
    private final Context mContext;
    public ViewClickListener viewClickListener;
    private int mMaxProgress = 100;
    private int mProgress = 30;
    private String mTxtHint1;
    private String mTxtHint2;

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mRectF = new RectF();
        mRectFBack1 = new RectF();
        mRectFBack2 = new RectF();
        mRectFBack3 = new RectF();
        mRectFBack4 = new RectF();
        mPaint = new Paint();
    }

//    @Override
//    public void setOnClickListener(@Nullable OnClickListener l) {
//        viewClickListener.viewClicked();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置画笔相关属性
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y


        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);

        // 绘制进度文案显示
        mPaint.setStrokeWidth(mTxtStrokeWidth);
        String text = mProgress + "";
        int textUnitHeight = height / 8;
        int textUnitWidth = (int) mPaint.measureText(text, 0, text.length());
        int textHeight = (int) (height / 2.1);
        mPaint.setTextSize(textHeight);
        int textWidth = (int) mPaint.measureText(text, 0, text.length());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, width / 2 - textWidth / 2-textUnitWidth-textUnitWidth/2, (int) (height / 2.1)+ textHeight / 4, mPaint);

        // %
        mPaint.setStrokeWidth(mTxtStrokeWidth);
        String textUnit = "%";
        mPaint.setTextSize(textUnitHeight);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(textUnit, width / 2 +textWidth/2 -textUnitWidth/2+5, (int) (height / 2.1) + textHeight / 4, mPaint);




        if (!TextUtils.isEmpty(mTxtHint1)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint1;
          int   textTitleHeight = height / 8;
            mPaint.setTextSize(textTitleHeight);
            mPaint.setColor(Color.WHITE);
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, (int) (height / 2.1) + textHeight-textTitleHeight-8 , mPaint);
        }


        if (!TextUtils.isEmpty(mTxtHint2)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint2;
            textHeight = height / 8;
            mPaint.setTextSize(textHeight);
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, mPaint);
        }

    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public String getmTxtHint1() {
        return mTxtHint1;
    }

    public void setmTxtHint1(String mTxtHint1) {
        this.mTxtHint1 = mTxtHint1;
    }

    public String getmTxtHint2() {
        return mTxtHint2;
    }

    public void setmTxtHint2(String mTxtHint2) {
        this.mTxtHint2 = mTxtHint2;
    }

    interface ViewClickListener {
        void viewClicked();
    }
}