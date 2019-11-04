package com.pitt.pittevent_lib;

/**
 * Author: Z.King.James
 * Declarations:事件实体类，在哪里，位置是什么，
 * Created on: 2019/10/28:19:48
 * Mail:mrzhaoxiaolin@163.com
 */
public class MotionEvent {

    public static final int ACTION_DOWN = 0;

    public static final int ACTION_UP = 1;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_CANCEL = 3;//如2图形叠加，又需要一个图形处理而另一个不处理，就来判定
    // ,让不需要处理
    //的控件，设为黑色
    private int actionMasked;//标记是哪个事件，
    private int x;
    private int y;//坐标

    public int getActionMasked() {
        return actionMasked;
    }

    public void setActionMasked(int actionMasked) {
        this.actionMasked = actionMasked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public MotionEvent(int actionMasked, int x, int y) {
        this.actionMasked = actionMasked;
        this.x = x;
        this.y = y;
    }


    //构造方法：


    public MotionEvent( int x, int y) {

        this.x = x;
        this.y = y;
    }

    public MotionEvent(){

    }


}
