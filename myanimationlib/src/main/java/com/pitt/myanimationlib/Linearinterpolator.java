package com.pitt.myanimationlib;

/**
 * Author: Z.King.James
 * Declarations:<!--网易1.4.1练习，手写动画框架,完成-->
 * Created on: 2019/11/4:16:56
 * Mail:mrzhaoxiaolin@163.com
 */
public class Linearinterpolator implements TimeInterpolator {
    @Override
    public float getInterpolator(float input) {
        return 0.5f*input;
    }
}
