package com.pitt.eventhander_mast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author: Z.King.James
 * Declarations://网易练习1，：问答题 （20分） 如何利用滤镜将一张彩色图变为一张灰色的图?
 * 提交内容：
 *
 * 1）主要思路/步骤
 *
 * 2）操作过程中的关键节点效果截图
 * Created on: 2019/11/2:15:19
 * Mail:mrzhaoxiaolin@163.com
 */
public class FilterView extends View {

    private Paint paint;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint = new Paint();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ttt);


        ColorMatrix matrix = new ColorMatrix(new float[]
                {0.33f, 0.33f, 0.44f, 0, 0
                        , 0.33f, 0.33f, 0.44f, 0, 0,
                        0.33f, 0.33f, 0.44f, 0, 0,
                        0, 0, 0, 1f, 0,

                });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(mBitmap, 500, 500, paint);
    }
}
