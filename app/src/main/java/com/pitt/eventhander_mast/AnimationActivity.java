package com.pitt.eventhander_mast;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.pitt.myanimationlib.Linearinterpolator;
import com.pitt.myanimationlib.MyObjectAnimator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: Z.King.James
 * Declarations:测试手写动画框架
 * Created on: 2019/11/4:22:46
 * Mail:mrzhaoxiaolin@163.com
 */
public class AnimationActivity extends AppCompatActivity {
   private Button btn_testanimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_test);
        btn_testanimation=findViewById(R.id.btn_testanimation);
        MyObjectAnimator animator=MyObjectAnimator.ofFloat(btn_testanimation,"ScaleX",
                1f,3f,3f,2f);
        animator.setInterpolator(new Linearinterpolator());
        animator.setDuration(3000);
        animator.start();
    }
}
