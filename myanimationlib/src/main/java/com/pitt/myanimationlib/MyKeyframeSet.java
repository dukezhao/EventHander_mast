package com.pitt.myanimationlib;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Z.King.James，
 * Declarations:worker ,工人，关键帧集合，关键帧管理类，
 * 初始化关键帧信息，返回对应的属性值
 * Created on: 2019/11/4:10:41
 * Mail:mrzhaoxiaolin@163.com
 */
public class MyKeyframeSet {

    //类型估值器,系统的，
    TypeEvaluator mEvaluator;

    //store keyFrames
    List<MyFloatKeyFrame> mkeyFrames;

    public MyKeyframeSet(MyFloatKeyFrame... keyFrame) {//参数是可变数组，
        this.mEvaluator = new FloatEvaluator();
        mkeyFrames = Arrays.asList(keyFrame);//关键帧集合，把数组变成集合
    }

    //对关键帧初始化
    public static MyKeyframeSet ofFloat(float[] values) {


        //对应的百分比多少，赋值


        if (values.length <= 0)//没有任何关键帧
        {
            return null;
        }
        int numKeyframes = values.length;
        //第一帧在循环外边赋值
        MyFloatKeyFrame keyFrame[] = new MyFloatKeyFrame[numKeyframes];
        keyFrame[0] = new MyFloatKeyFrame(0, values[0]);//把value[0]赋值给第一帧
        //循环赋值
        for (int i = 1; i < numKeyframes; i++) {
            keyFrame[i] = new MyFloatKeyFrame((float) i / (numKeyframes - 1), values[i]);

        }
        return new MyKeyframeSet(keyFrame);

    }


    //获取当前百分比对应的具体属性值，需要考虑不同区间（2个关键帧之间），缩小or放大的情况
    public Object getValue(float fraction) {
        MyFloatKeyFrame prevKeyFrame=mkeyFrames.get(0);//首帧
        for(int i=0;i<mkeyFrames.size();i++)
        {
           MyFloatKeyFrame nextKeyFrame= mkeyFrames.get(i);//第二帧
            if(fraction<nextKeyFrame.getFraction())
            {
                //当前百分比在此区间内
                //计算2帧之间对应的百分比
                //定义： 当前百分比-prekeyframe的百分比的差/next帧百分比-preframe百分比的差
                float intervalFraction=(fraction-prevKeyFrame.getFraction())/
                        (nextKeyFrame.getFraction()-prevKeyFrame.getFraction());

                //通过估值器返回对应的值 todo repeat to understand
                //prefraction+变化的百分比*（下一帧-上一帧的问题）
                return mEvaluator==null?
                        prevKeyFrame.getFraction()+intervalFraction*(nextKeyFrame.getValue()-prevKeyFrame.getValue()):
                        ((Number)mEvaluator.evaluate(intervalFraction,prevKeyFrame.getValue(),nextKeyFrame.getValue()));   //如果有估值器

            }
            prevKeyFrame=nextKeyFrame;

        }


        //对应的帧不够
        return mkeyFrames.get(mkeyFrames.size()-1).getValue();
    }
}
