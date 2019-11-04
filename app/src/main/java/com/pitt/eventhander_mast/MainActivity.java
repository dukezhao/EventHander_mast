package com.pitt.eventhander_mast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //测试手写动画框架
        //setContentView(R.layout.activity_main);
       /* button = findViewById(R.id.button);
        //Scalex也可运行，但是我们一般用小写scalex,但是SCalex就不行
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "SaleX", 1f, 2f, 3f, 4f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(3000);
        animator.start();*/

       //网易练习1，第一大题
     /*   问答题 （20分） 如何利用滤镜将一张彩色图变为一张灰色的图?
                提交内容：

        1）主要思路/步骤

        2）操作过程中的关键节点效果截图

        */
        setContentView(new FilterView(this));


    }
}
