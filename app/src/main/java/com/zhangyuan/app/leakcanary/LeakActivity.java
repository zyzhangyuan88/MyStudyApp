package com.zhangyuan.app.leakcanary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhangyuan.app.R;

/**
 * Created by zhangyuan on 17/11/24.
 */

public class LeakActivity extends Activity {

    public class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MyHandler handler = new MyHandler();
        handler.sendMessageDelayed(Message.obtain(), 10 * 60 * 1000);
    }
}
