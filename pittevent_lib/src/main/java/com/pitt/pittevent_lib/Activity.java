package com.pitt.pittevent_lib;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/10/28:19:48
 * Mail:mrzhaoxiaolin@163.com
 */
public class Activity {

    public static void main(String[] args) {
/*
        ViewGroup vp = new ViewGroup(0, 0, 1080, 1920);
        vp.setName("顶层容器");

        ViewGroup vp1 = new ViewGroup(0, 0, 500, 500);
        vp1.setName("第二层容器");

        View view = new View(0, 0, 200, 200);
        view.setName("子控件");

        vp1.addView(view);
        vp.addView(vp1);

        vp.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("顶层容器 ouTouch事件");
                return false;
            }
        });


        vp1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("第二层容器 ouTouch事件");
                return false;
            }
        });

        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("子控件ouTouch事件");
                return false;
            }
        });

        //发送点击事件
        MotionEvent motionEvent=new MotionEvent(100,100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);

        //viewgroup顶层容器分发事件
        vp.dispatchTouchEvent(motionEvent);*/

        ViewGroup viewGroup = new ViewGroup(0, 0, 1080, 1920);
        viewGroup.setName("顶层容器");
        ViewGroup viewGroup1 = new ViewGroup(0, 0, 500, 500);
        viewGroup1.setName("第二层容器");

        View view = new View(0, 0, 200, 200);
        view.setName("子控件");
        View view2 = new View(0, 0, 300, 300);
        view2.setName("子控件2222");

        viewGroup1.addView(view);
        viewGroup1.addView(view2);

        viewGroup.addView(viewGroup1);

        viewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println(System.currentTimeMillis() + "  顶层容器的  onTouch事件");
                return false;
            }
        });
        viewGroup1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println(System.currentTimeMillis() + "  第二层容器的  onTouch事件");
                return false;
            }
        });
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println(System.currentTimeMillis() + "  子控件View  onTouch事件");
                return false;
            }
        });
       /* view2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println(System.currentTimeMillis() + "  子控件View222  onTouch事件");
                return false;
            }
        });
        view2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        view.setOnClickListener(new OnClickListener() {
            @Override
            public boolean onClick(View view) {
                return false;
            }
        });
        //view2点击事件接收消费后，view不能再接收，消费事件
   /*    view2.setOnClickListener(new OnClickListener() {
           @Override
           public boolean onClick(View view) {
               return false;
           }
       });*/
        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);
        //顶层容器分发事件
        viewGroup.dispatchTouchEvent(motionEvent);

        System.out.println("第二次事件-------------------  " + System.currentTimeMillis());

        MotionEvent motionEvent2 = new MotionEvent(100, 100);
        motionEvent2.setActionMasked(MotionEvent.ACTION_UP);
        viewGroup.dispatchTouchEvent(motionEvent2);


    }
}
