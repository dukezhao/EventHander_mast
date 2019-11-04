package com.pitt.eventhander_mast;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/3:19:10
 * Mail:mrzhaoxiaolin@163.com
 */
public class TestImgMovActivity extends Activity {
    ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testimg);
        iv=findViewById(R.id.iv);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //move
                       // iv.setTranslationX(500);
                        //or use animation
                        ObjectAnimator animator=ObjectAnimator.ofFloat(iv,"translationX",250.f);
                        animator.setDuration(100);
                        animator.start();

                  /*  break;

                    case MotionEvent.ACTION_MOVE:*/
                        //scale
                        PropertyValuesHolder scaleXValueHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                                Keyframe.ofFloat(0f, 5.0f),
                                Keyframe.ofFloat(0.25f, 0.9f),
                                Keyframe.ofFloat(0.5f, 2.0f),
                                Keyframe.ofFloat(0.75f, 10f),
                                Keyframe.ofFloat(1.0f, 1.0f));

                        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                                Keyframe.ofFloat(0f, 1.0f),
                                Keyframe.ofFloat(0.25f, 0.9f),
                                Keyframe.ofFloat(0.5f, 2.0f),
                                Keyframe.ofFloat(0.75f, 10f),
                                Keyframe.ofFloat(1.0f, 1.0f)
                        );
                    float shakeDegrees=20f;
                        PropertyValuesHolder rotateValueHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                                Keyframe.ofFloat(0f, 0f),
                                Keyframe.ofFloat(0.1f, -shakeDegrees),
                                Keyframe.ofFloat(0.2f, shakeDegrees),
                                Keyframe.ofFloat(0.3f, -shakeDegrees),
                                Keyframe.ofFloat(0.4f, shakeDegrees),
                                Keyframe.ofFloat(0.5f, -shakeDegrees),
                                Keyframe.ofFloat(0.6f, shakeDegrees),
                                Keyframe.ofFloat(0.7f, -shakeDegrees),
                                Keyframe.ofFloat(0.8f, shakeDegrees),
                                Keyframe.ofFloat(0.9f, -shakeDegrees),
                                Keyframe.ofFloat(1.0f, shakeDegrees));

                        animator = ObjectAnimator.ofPropertyValuesHolder(iv, scaleXValueHolder, scaleYValuesHolder, rotateValueHolder);
                        animator.setDuration(3000);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setRepeatCount(5);
                        animator.start();

               /*         break;
                    case MotionEvent.ACTION_UP:*/
                        //rotate
                        Animation animation= AnimationUtils.loadAnimation(TestImgMovActivity.this,R.anim.anmation);
                        LinearInterpolator lin=new LinearInterpolator();
                        animation.setInterpolator(lin);
                        iv.startAnimation(animation);
                        break;
                }
                return false;
            }
        });
    }
}
