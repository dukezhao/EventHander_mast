package com.pitt.neteasesamelib;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/1:16:01
 * Mail:mrzhaoxiaolin@163.com
 */


import java.util.ArrayList;
import java.util.List;

public class ViewGroup extends View {
    //存放子控件
    List<View> childList = new ArrayList<>();
    private View[] mChildren = new View[0];
    private TouchTarget mFirstTouchTarget;//存放事件分发顺序

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    public void addView(View view) {
        //添加自控件
        if (view == null) {
            return;
        }
        childList.add(view);//
        mChildren = childList.toArray(new View[childList.size()]);
    }

    //事件分发的入口

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(name + "  dispatchTouchEvent  " + System.currentTimeMillis());
        boolean handled = false;
        //判断是否需要拦截
        TouchTarget newTouchTarget = null;
        boolean intercepted = onInterceptTouchEvent(ev);
        int actionMasked = ev.getActionMasked();
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {
            //down事件
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                //循环遍历控件 倒叙遍历
                View[] children = mChildren;
                //耗时 在源码中会根据Z轴重新排序
                for (int i = children.length - 1; i >= 0; i--) {
                    //获取当前的子控件(确实应该这么写)
                    View child = children[i];
                    //child能否去接受事件
                    if (!child.isContainer(ev.getX(), ev.getY())) {
                        //不在控件内
                        continue;
                    }
                    //child能接受事件 分发给子控件
                    if (dispatchTransformedTouchEvent(ev, child)) {
                        //view[]记录? 责任链模式 Message
                        handled = true;
                        newTouchTarget = addTouchTarget(child);
                        break;
                    }
                }

            }
        }
        //没有事件链表（无子控件消费事件????自己用）
        if (mFirstTouchTarget == null) {
            handled = dispatchTransformedTouchEvent(ev, null);
        } else {
            //有链表，则按照链表传递事件
            TouchTarget target = mFirstTouchTarget;
            while (target != null) {
                TouchTarget next = target.next;
                if (target == newTouchTarget) {
                    handled = true;
                } else {
                    handled = dispatchTransformedTouchEvent(ev, target.child);
                }
                target = next;
            }
        }
        return handled;
    }

    private TouchTarget addTouchTarget(View child) {
        TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        //当前缓存的View
        private View child;
        //回收池
        private static TouchTarget sRecycledBin;
        //size
        private static int sRecycledCount;
        private static final Object sRecycleLock = new Object[0];
        //next
        public TouchTarget next;

        public static TouchTarget obtain(View child) {
            TouchTarget target;
            synchronized (sRecycleLock) {
                if (sRecycledBin == null) {
                    target = new TouchTarget();
                } else {
                    target = sRecycledBin;
                }
                sRecycledBin = target.next;
                sRecycledCount--;
                target.next = null;
            }
            target.child = child;
            return target;
        }

        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经回收过了");
            }
            synchronized (sRecycleLock) {
                if (sRecycledCount < 32) {
                    next = sRecycledBin;
                    sRecycledBin = this;
                    sRecycledCount++;
                }
            }
        }
    }

    //分发事件到子控件控件
    private boolean dispatchTransformedTouchEvent(MotionEvent ev, View child) {
        boolean handled = false;
        //
        if (child != null) {
            //传给子控件
            handled = child.dispatchTouchEvent(ev);
        } else {
            handled = super.dispatchTouchEvent(ev);
        }
        return handled;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}

