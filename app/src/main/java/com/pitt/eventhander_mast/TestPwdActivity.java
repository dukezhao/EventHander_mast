package com.pitt.eventhander_mast;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2019/11/3:17:54
 * Mail:mrzhaoxiaolin@163.com
 */
public class TestPwdActivity extends Activity implements MyPwdView.OnPwdInputComplete {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testpwd);
        MyPwdView myPwdView;
        myPwdView=findViewById(R.id.mypwd);
        myPwdView.setOnPasswordInputComplete(this);
    }

    @Override
    public void onPwdInputComplete(String s) {

    }
}
