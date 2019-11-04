package com.pitt.pittevent_lib;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/10/28:19:48
 * Mail:mrzhaoxiaolin@163.com
 */
public class View {
//点击位置是否在控件范围内
    //view的位置坐标

    private int left;
    private int bottom;
    private int right;
    private int top;
    private OnTouchListener onTouchListener;
    private OnClickListener onClickListener;

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }

    public View() {
    }

    public OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //是否在view范围内
    public boolean isContainer(int x, int y) {
        if (x > left && x < right && y > top && y < bottom) {
            return true;
        } else {
            return false;
        }
    }

    //分发事件方法，true 处理了事件，false 没有处理事件,why view 有这个分发处理事件方法，
    //因为接收事件分发（from veiwgroup）,在view里不具备时间分发能力
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(name+" View dispatchTouchEvent");
        //消费：
        boolean result = false;

        //判断一下是否有onTouchListener ，有则执行,不为空，就执行onTouchListener.onTouch(this,ev),todo :这种2行代码并做一行的写法，可以参考，
        if (onTouchListener != null && onTouchListener.onTouch(this, ev))//onTouchListener,可以在Activity里重写；
        {
            result= true;
        }
        //如果（在onTouchListener里）沒有消費事件，執行此View本身的onTouchEvebt,即下边这个onTouchEvent方法。

        if (!result && onTouchEvent(ev)) {
            result = true;
        }
        return result;
    }

    //处理事件方法,true:处理了，false:没处理
    private boolean onTouchEvent(MotionEvent ev) {
        System.out.println(name+"  onTouchEvent ");
        //判断当前事件是否设置点击di事件
        if (onClickListener != null)//认为设置onclickListener就表示事件被触发了，
        {
            onClickListener.onClick(this);
            return true;
        }
        return false;
    }


    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + name;
    }




}
