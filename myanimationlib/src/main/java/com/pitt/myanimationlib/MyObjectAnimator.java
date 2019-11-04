package com.pitt.myanimationlib;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Author: Z.King.James
 * Declarations:  the objectAnimator customized , call “the little master of workers ；包工头”
 * Created on: 2019/11/4:10:27
 * Mail:mrzhaoxiaolin@163.com
 */
public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallback {


    //需要执行的动画对象： 弱引用
    private WeakReference<View> mTarget;

    //属性值管理类
    private MyFloatPropertyValueHolder mFloatPropertyValueHolder;
    private int index = 0;//当前帧
    private TimeInterpolator mTimeInterpolator;

    //动画时长
    private long mDuration = 0;


    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TimeInterpolator getTimeInterpolator() {
        return mTimeInterpolator;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        mTimeInterpolator = timeInterpolator;
    }


    //构造fun
    public MyObjectAnimator(View target, String propertyName, float... values) {
        mTarget = new WeakReference<>(target);
        mFloatPropertyValueHolder = new MyFloatPropertyValueHolder(propertyName, values);//
        //初始化后，所有属性，
    }

    /*
     * @params prppertyName 是调用的具体方法，属性的str名
     * @params values: 关键帧
     * */
    public static MyObjectAnimator ofFloat(View target, String propertyName, float... values) {
        MyObjectAnimator anim = new MyObjectAnimator(target, propertyName, values);

        return anim;

    }


    //每隔16ms执行一次
    @Override
    public boolean doAnimationFrame(long currentTime) {

        //后续效果渲染
        // mFloatPropertyValueHolder改变target对象对应的属性值,

        // 获取动画总帧数
        float total = mDuration / 16;

        //拿到执行百分比：（index/total）
        float fraction = (index++) / total;

        //通过插值器去改变对应的执行百分比
        if (mTimeInterpolator != null) {
            fraction = mTimeInterpolator.getInterpolator(fraction);
        }

        //循环，repeat
        if (index >= total) {
            index = 0;

        }
        //交给mFloatPropertyValueHolder改变target对象对应的属性值·

        mFloatPropertyValueHolder.setAnimatedValue(mTarget.get(), fraction);
        return false;
    }

    //开启动画

    public void start() {
        //交给mFloatPropertyValueHolder改变对应的属性值，iiaogeidoAnimationFrame执行
        mFloatPropertyValueHolder.setupSetter();
        VSYNCManager.getInstance().add(this);
    }

}
