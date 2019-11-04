package com.pitt.eventhander_mast;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Author: Z.King.James
 * Declarations::网易练习2 第2大题,自定义 有问题
 * Created on: 2019/11/3:13:33
 * Mail:mrzhaoxiaolin@163.com
 */
public class PixSuitableLayout extends RelativeLayout {
    private boolean flag=false;
    public PixSuitableLayout(Context context) {
        super(context);
    }

    public PixSuitableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PixSuitableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!flag){
            float scaleX = Utils.getInstance(getContext()).getHorizontalScale();
            float scaleY = Utils.getInstance(getContext()).getVerticalScale();

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);

 flag=true;
            }

           
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
