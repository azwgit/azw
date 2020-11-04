package com.example.bq.edmp.utils;

/**
 * Created by wft on 2016/11/29.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class FlowLayout extends LinearLayout {

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context) {
        this(context, null, 0);
    }


    /** 行间隔 **/
    private int margineV = 20;
    /** 列间隔 **/
    private int margineH = 20;

    public void setMargineH(int margineH){
        this.margineH=margineH;
    }

    private ArrayList<LineContainer> mLines = new ArrayList<LineContainer>();


    private class LineContainer {
        private int totalWidth = 0;
        private int totalHeight = 0;
        private List<View> views = new ArrayList<View>();

        public int getTotalWidth() {
            return totalWidth;
        }

        public int getTotalHeight() {
            return totalHeight;
        }

        public List<View> getViews() {
            return views;
        }


        public boolean add(int space, View childAt) {

            if (views.size() == 0) {

                views.add(childAt);
                totalWidth = childAt.getMeasuredWidth();
                totalHeight = childAt.getMeasuredHeight();
                return true;
            } else {

                int needWidth = margineH + childAt.getMeasuredWidth();

                if (space < needWidth) {
                    return false;
                } else {
                    views.add(childAt);

                    totalWidth += needWidth;
                    if (totalHeight < childAt.getMeasuredHeight()) {
                        totalHeight = childAt.getMeasuredHeight();
                    }
                    return true;
                }

            }

        }

    }

    private LineContainer mLine = null;
    private int mLayoutWidth;;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mLines.clear();

        mLayoutWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();

        int count = getChildCount();

        int space = 0;
        for (int index = 0; index < count; index++) {
            View childAt = getChildAt(index);

            childAt.measure(0, 0);
            int cHeight = childAt.getMeasuredHeight();
            int cWidth = childAt.getMeasuredWidth();
            System.out.println(index + "->" + cWidth + "," + cHeight);

            if (mLine == null) {
                mLine = new LineContainer();

                mLines.add(mLine);

                space = mLayoutWidth;
            }

            boolean success = mLine.add(space, childAt);
            if (success) {

                space = mLayoutWidth - mLine.getTotalWidth();
            } else {

                mLine = new LineContainer();

                mLines.add(mLine);

                space = mLayoutWidth;

                mLine.add(space, childAt);
                space = mLayoutWidth - mLine.getTotalWidth();
            }
        }

        int mLayoutHeight = 0;
        for (int row = 0; row < mLines.size(); row++) {

            LineContainer eachLine = mLines.get(row);

            mLayoutHeight += (eachLine.getTotalHeight() + margineV);
        }

        mLayoutHeight -= margineV;

        setMeasuredDimension(mLayoutWidth, mLayoutHeight);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineLeft=0;
        int lineTop=0;

        int lineCount = mLines.size();

        for (int row = 0; row < lineCount; row++) {

            LineContainer lineContainer = mLines.get(row);

            List<View> views = lineContainer.getViews();

            int eachSpace=0;

            if (row == 0) {
                lineTop=0;
            }else
            {
                lineTop += (margineV + lineContainer.getTotalHeight());
            }

            for (int column = 0; column < views.size(); column++) {
                View view = views.get(column);

                if(column==0)
                {
                    lineLeft=0;
                }else
                {

                    View viewPre = views.get(column-1);

                    lineLeft=viewPre.getRight()+margineH;
                }


                int left=lineLeft;
                int top=lineTop;

                int right=left+view.getMeasuredWidth()+eachSpace;//

                int bottom=top+view.getMeasuredHeight();

                view.layout(left, top, right, bottom);


                int size = right - left;
                int mode = MeasureSpec.EXACTLY;

                int newMeasureWidth = MeasureSpec.makeMeasureSpec(size, mode);

                view.measure(newMeasureWidth, 0);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
    }
}