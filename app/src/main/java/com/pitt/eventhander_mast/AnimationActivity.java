package com.pitt.eventhander_mast;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.pitt.myanimationlib.Linearinterpolator;
import com.pitt.myanimationlib.MyObjectAnimator;


/**
 * Author: Z.King.James
 * Declarations:测试手写动画框架，<!--网易1.4.1练习，手写动画框架,完成-->
 * Created on: 2019/11/4:22:46
 * Mail:mrzhaoxiaolin@163.com
 */
public class AnimationActivity extends Activity {
   private Button btn_testanimation;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_test);
        btn_testanimation=findViewById(R.id.btn_testanimation);
       MyObjectAnimator animator=MyObjectAnimator.ofFloat(btn_testanimation,"ScaleX",
                1f,2f,1f,3f,1f);
        animator.setInterpolator(new Linearinterpolator());
        animator.setDuration(5000);
        animator.start();
    }
}
