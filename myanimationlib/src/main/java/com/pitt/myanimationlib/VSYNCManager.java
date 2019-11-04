package com.pitt.myanimationlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Z.King.James
 * Declarations:模拟vsnyc信号
 * Created on: 2019/11/4:10:45
 * Mail:mrzhaoxiaolin@163.com
 */
public class VSYNCManager {
    //单例模式
    private static final VSYNCManager instance = new VSYNCManager();
    private List<AnimationFrameCallback> list = new ArrayList();

    public static final VSYNCManager getInstance() {
        return instance;

}
    //构造线程，每16ms间隔开启动画
    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    //每隔16ms执行动画，
                    Thread.sleep(16);
                    //遍历，
                    for(AnimationFrameCallback animationFrameCallback:list)
                    {
                        animationFrameCallback.doAnimationFrame(System.currentTimeMillis());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public VSYNCManager(){
        new Thread(mRunnable).start();
    }

    //可以多个动画同时执行,所以用集合将 回调存放；
    //接口回调

    ///让多个动画同时执行，
    interface AnimationFrameCallback {
        boolean doAnimationFrame(long currentTime);

    }

    //添加回调
    public void add(AnimationFrameCallback animationFrameCallback) {
        list.add(animationFrameCallback);
    }

    //VSYNC信号模拟 ，用线程，每16ms发信号，去执行 doAnimationFrame，之后执行每一帧的绘制，就模拟了VSNNC信号，



}
