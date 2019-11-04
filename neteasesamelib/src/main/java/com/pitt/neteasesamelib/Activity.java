package com.pitt.neteasesamelib;

import com.pitt.neteasesamelib.OnClickListener;
import com.pitt.neteasesamelib.OnTouchListener;
/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/1:16:02
 * Mail:mrzhaoxiaolin@163.com
 */
public class Activity {
    public static void main(String[] args) {
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
        view2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println(System.currentTimeMillis() + "  子控件View222  onTouch事件");
                return false;
            }
        });
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
