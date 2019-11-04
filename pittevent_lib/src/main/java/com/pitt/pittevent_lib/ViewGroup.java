package com.pitt.pittevent_lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/10/28:19:48
 * Mail:mrzhaoxiaolin@163.com
 */
public class ViewGroup extends View {

    //存放子控件
    List<View> childList = new ArrayList();//源码中无此集合
    private View[] mChildren = new View[0];
    private TouchTarget mFirstTouchTarget;//存放事件分发的顺序

    //构造方法

    public ViewGroup(List<View> childList, View[] children) {
        this.childList = childList;
        mChildren = children;
    }

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    //添加子控件

    public void addView(View view) {
        if (view == null) {
            return;
        }
        childList.add(view);
        mChildren = childList.toArray(new View[childList.size()]);
    }

    //事件分发的入口
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(name + " ,dispatchTouchEvent");
        //判断是否需要拦截
        boolean handled = false;//是否处理过
        TouchTarget newTouchTarget = null;

        boolean intercepted = onInterceptTouchEvent(ev);

        //判断动作类型
        int actionMasked = ev.getActionMasked();
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted)//非cancel 且未被拦截
        {
            //down事件
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                //循环遍历控件
                View[] children = mChildren;//因为源码中，需要排序，避免对原数组顺序做出更改。
                for (int i = children.length - 1; i >= 0; i--)//views中越靠近后边的，z轴越高的也越可能有限响应事件，
                //所以倒序遍历
                {
                    View child = children[i];//已经根据z轴排序过的
                    // child能否接受事件
                    if (!child.isContainer(ev.getX(), ev.getY())) {
                        continue;//点击处坐标不在空间内，
                    }
                    if (dispatchTransformedTouchEvent(ev, child)) {
                        // 事件消费后，记录 用View[]?,不，责任链模式；仿照Message.java里的sPool，回收池
                        handled = true;//事件已经被处理
                        newTouchTarget = addTouchTarget(child);//newTouchTarget：当前最新消费事件的控件
                        break;///一旦有控件消费了事件，其他控件不要想再消费了，退出循环，


                    }
                }

            }
        }
        //没有事件链表(没子控件消费事件的话，自己用）
        if (mFirstTouchTarget == null) {
            handled = dispatchTransformedTouchEvent(ev, null);//NULL ：这里子控件传null,虽然还是事件分发方法：
            //但是后边所有事件不会分发给子控件了
        } else {//有链表，则按照链表传递事件

            TouchTarget target = mFirstTouchTarget;//拿到链表头
            while (target != null) {
                TouchTarget next = target.next;
                if (target == newTouchTarget) {
                    handled = true;

                } else {
                    handled = dispatchTransformedTouchEvent(ev, target.child);//继续分发事件到子控件
                }
                target = next;
            }

        }
        return handled;
    }


    //添加到回收池（列表）的方法 **关键代码
    public TouchTarget addTouchTarget(View child) {
        TouchTarget target = TouchTarget.obtain(child);
        target.next = mFirstTouchTarget;
        mFirstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        //当前缓存view
        private View child;
        //回收池,也是链表结构
        private static TouchTarget sRecycledBin;

        //size
        private static int sRecycledCount;

        //同步锁
        private static final Object sRecycleLock = new Object[0];

        //指针
        public TouchTarget next;//类型就是类自己，

        //回收池方法:从回收池里取出，需要绑定当前缓存:view :child
        public static TouchTarget obtain(View child) {//obtain可能同时触发多个，所以用同步锁去管理执行；
            TouchTarget target;
            synchronized (sRecycleLock) {
                //如果回收池有对象，就从回收池里获取
                if (sRecycledBin == null) {
                    target = new TouchTarget();
                } else {
                    target = sRecycledBin;//指向首节点

                }
                sRecycledBin = target.next;//sRecycledBin指向下一个
                sRecycledCount--;
                target.next = null;//断开链表；

            }
            target.child = child;
            return target;


        }

        //回收方法：

        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经回收过了");
            }

            // 没有回收过
            synchronized (sRecycleLock) {
                if (sRecycledCount < 32) {//表示ui如果超过32个层级，可能就触发不了，
                    next = sRecycledBin;
                    sRecycledBin = this;
                    sRecycledCount++;

                }
            }
        }

    }

    //分发事件到子控件
    private boolean dispatchTransformedTouchEvent(MotionEvent ev, View child) {
        //return true , 事件消费
        boolean handled = false;
        if (child != null) {
            handled = child.dispatchTouchEvent(ev);

        } else {
            handled = super.dispatchTouchEvent(ev);
        }
        return handled;

    }


    //只在容器内有，如果父控件拦截事件，子控件就不能再用这个事件，默认不拦截，return false;
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    //请求父亲容器不拦截,如：嵌套滑动 todo 自己看源码，找到后添加过来


}
