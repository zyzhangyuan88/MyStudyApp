package com.zhangyuan.app.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.zhangyuan.app.R;

import java.io.File;


/**
 *
 *
 *
 *
 */

 public class UpdateAppReceiver extends BroadcastReceiver {


    NotificationManager nm;
    NotificationCompat.Builder builder;
    Notification notification;

    @Override
    public void onReceive(Context context, Intent intent) {

        int notifyId = 1;
        int progress = intent.getIntExtra("progress", 0);
        String title = intent.getStringExtra("title");

        if(builder == null){
            builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("正在下载 "+title);
            builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
            notification = builder.build();
        }
        builder.setProgress(100,progress,false);


        if(nm == null){
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        nm.notify(notifyId,builder.getNotification());


        if (progress == 100){
            if (nm!=null)nm.cancel(notifyId);

            if (DownloadAppUtils.downloadUpdateApkFilePath != null) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                File apkFile = new File(DownloadAppUtils.downloadUpdateApkFilePath);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(
                            context, context.getPackageName() + ".fileprovider", apkFile);
                    i.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    i.setDataAndType(Uri.fromFile(apkFile),
                            "application/vnd.android.package-archive");
                }
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }

}
