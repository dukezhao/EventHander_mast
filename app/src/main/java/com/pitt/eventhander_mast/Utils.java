package com.pitt.eventhander_mast;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Author: Z.King.James
 * Declarations:网易练习2 第2大题
 * Created on: 2019/11/3:13:34
 * Mail:mrzhaoxiaolin@163.com
 */
public class Utils {
    private static Utils utils;


    //屏幕显示宽高
    private int mDisplayWidth;
    private int mDisplayHeight;

    //参考设备宽高
    private static final float STANDARD_WIDTH =360;// 360;
    private static final float STANDARD_HEIGHT =640 ;// 640;


    private Utils(Context ctx) {

        //get screen width ,height
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager mgr = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
            if (mgr != null) {
                DisplayMetrics metrics = new DisplayMetrics();
                mgr.getDefaultDisplay().getMetrics(metrics);
                if (metrics.widthPixels > metrics.heightPixels) {
                    mDisplayWidth = metrics.heightPixels;
                    mDisplayHeight = metrics.widthPixels;
                } else {
                    mDisplayWidth = metrics.widthPixels;
                    mDisplayHeight = metrics.heightPixels - getStatusBarHeight(ctx);//减去状态栏
                }
            }
        }

    }

    public int getStatusBarHeight(Context context) {
        int resID = context.getResources().getIdentifier("status_bar_height", "dimmen", "android");
        if (resID > 0) {
            return context.getResources().getDimensionPixelSize(resID);
        }
        return 0;//若无resId说明没有statusBar
    }

    public static Utils getInstance(Context context) {
        if (utils == null) {
            utils = new Utils(context.getApplicationContext());
        }
        return utils;
    }

    //获取水平方向上缩放比例
    public float getHorizontalScale() {
        return  mDisplayWidth /STANDARD_WIDTH ;

    }

    //获取垂直方向上缩放比例
    public float getVerticalScale() {
        return    mDisplayHeight/STANDARD_HEIGHT;

    }
}
