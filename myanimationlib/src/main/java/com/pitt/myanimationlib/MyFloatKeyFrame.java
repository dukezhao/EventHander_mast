package com.pitt.myanimationlib;

/**
 * Author: Z.King.James
 * Declarations:产品； 关键帧
 * Created on: 2019/11/4:10:43
 * Mail:mrzhaoxiaolin@163.com
 */
public class MyFloatKeyFrame {

    //当前百分比
    float fraction;

    //当前帧对应的属性值
    float mValue;

    //当前帧对应的值的类型
    Class mValueType;

    public MyFloatKeyFrame(float fraction, float value) {
        this.fraction = fraction;
        mValue = value;
        mValueType=float.class;//float类型
    }


    public Class getValueType() {
        return mValueType;
    }

    public void setValueType(Class valueType) {
        mValueType = valueType;
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
    }

    public float getValue() {
        return mValue;
    }
}







