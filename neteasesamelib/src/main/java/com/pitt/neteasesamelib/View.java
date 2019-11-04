package com.pitt.neteasesamelib;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/1:16:00
 * Mail:mrzhaoxiaolin@163.com
 */

public class View {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public View() {
    }

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    private OnTouchListener onTouchListener;
    private OnClickListener onClickListener;

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

    public boolean isContainer(int x, int y) {
        if (x > left && x < right && y > top && y < bottom) {
            return true;
        }
        return false;
    }

    /**
     * 接收事件分发（在这里不具备事件分发的能力）
     *
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(name + "View  dispatchTouchEvent  " + System.currentTimeMillis());
        //消费
        boolean result = false;
        //是否有onTouchListener 有则执行
        if (onTouchListener != null && onTouchListener.onTouch(this, ev)) {
            result = true;
        }
        //没有消费事件 执行onTouchEvent
        if (!result && onTouchEvent(ev)) {
            result = true;
        }
        return result;
    }

    private boolean onTouchEvent(MotionEvent ev) {
        //判断当前控件是否有设置点击事件
//        System.out.println(name + "   onTouchEvent");
        if (onClickListener != null) {
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
