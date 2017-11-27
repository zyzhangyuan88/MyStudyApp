package com.zhangyuan.app.leakcanary;

import android.app.Activity;
import android.os.Bundle;

import com.squareup.leakcanary.RefWatcher;
import com.zhangyuan.app.R;
import com.zhangyuan.app.app.App;

/**
 * Created by zhangyuan on 17/11/24.
 */

public class LeakCanaryTestActivity extends Activity {


    // 静态的内部类实例
    private static OutterClass.Inner innerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        OutterClass outterClass = new OutterClass();
        innerClass = outterClass.new Inner();

        RefWatcher refWatcher = App.getRefWatcher(this);
        refWatcher.watch(outterClass);// 监控的对象

        outterClass = null;
    }


}
