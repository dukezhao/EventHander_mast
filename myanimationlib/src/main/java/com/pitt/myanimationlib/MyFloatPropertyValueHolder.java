package com.pitt.myanimationlib;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: Z.King.James
 * Declarations:"动画任务属性值管理类， the real master who works , under little worker master ，工头 "
 * Created on: 2019/11/4:10:38
 * Mail:mrzhaoxiaolin@163.com
 */
public class MyFloatPropertyValueHolder {

    //属性名
    String mPropertyName;
    //属性类型 float
    Class mValueType;

    //通过反射，对具体属性值的更改
    Method mSetter = null;


    //关键帧管理类，
    MyKeyframeSet mKeyFrameset;

    //初始化时候记录属性名，记录关键帧给关键帧管理对象，
    public MyFloatPropertyValueHolder(String propertyName, float... values) {
        this.mPropertyName = propertyName;
        mValueType = float.class;
        //value交给关键帧管理初始化
        mKeyFrameset = MyKeyframeSet.ofFloat(values);


    }

    //通过反射获取控件对应的方法:
    public void setupSetter() {
        char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
        String theRest = mPropertyName.substring(1);
        String methodName = "set" + firstLetter + theRest;//til this, get the method name

        //
        try {
            mSetter = View.class.getMethod(methodName, float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
        //通过反射给控件设置响应的属性值
    public void setAnimatedValue(View view, float fraction) {
        Object value=mKeyFrameset.getValue(fraction);
        try {
            mSetter.invoke(view,value);//INVOke 表示：注入具体值value到view里
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
