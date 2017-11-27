package com.zhangyuan.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhangyuan.app.leakcanary.LeakCanaryTestActivity;
import com.zhangyuan.app.ui.activity.WxActivity;
import com.zhangyuan.app.update.DownLoadAppService;
import com.zhangyuan.app.update.DownloadAppUtils;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String url = "http://down1.uc.cn/down2/zxl107821.uc/miaokun1/UCBrowser_V11.5.8.945_android_pf145_bi800_(Build170627172528).apk";
//                String versionName = "34.56.32";
//
//                DownloadAppUtils.download(MainActivity.this,url,versionName);
                MainActivity.this.startActivity(new Intent(MainActivity.this, LeakCanaryTestActivity.class));
            }
        });

        findViewById(R.id.fragment_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DownLoadAppService.class);
                MainActivity.this.startService(intent);
            }
        });



    }
}
