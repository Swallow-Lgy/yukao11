package com.example.dell.yukao1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LayoutViewGroup extends LinearLayout {
    //最大孩子的高度
    int mChildMaxHeigth;
    //左右边距
    int mHspace=20;
    //上下间距
    int mVspace=20;
    public LayoutViewGroup(Context context) {
        super(context);
    }

    public LayoutViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //设置单个布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeigth = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        findMaxChildMaxHeigth();
        //初始化数据
        int left=0,top=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (left!=0){
                if (view.getMeasuredWidth()+left>sizeWidth){
                    top+=mChildMaxHeigth+mVspace;
                    left=0;
                }

            }
            left+=view.getMeasuredWidth()+mHspace;
        }
        setMeasuredDimension(sizeWidth,(top+mChildMaxHeigth)>
        sizeHeigth?sizeHeigth:top+mChildMaxHeigth
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChildMaxHeigth();
        int left=0,top=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (left!=0){
                if (left+view.getMeasuredWidth()>getWidth()){
                    top+=mChildMaxHeigth+mVspace;
                    left=0;
                }
            }
            view.layout(left,top,view.getMeasuredWidth()+left
            ,mChildMaxHeigth+top
            );
            left+=view.getMeasuredWidth()+mHspace;
        }
    }
    //寻找最大孩子的高度
    public void findMaxChildMaxHeigth(){
        mChildMaxHeigth=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if (view.getMeasuredHeight()>mChildMaxHeigth){
                mChildMaxHeigth = view.getMeasuredHeight();
            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
