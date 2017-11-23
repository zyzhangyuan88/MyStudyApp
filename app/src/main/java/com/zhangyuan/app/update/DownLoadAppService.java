package com.zhangyuan.app.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.zhangyuan.app.R;

import java.io.File;

/**
 *
 * Created by zhangyuan on 17/11/22.
 */

public class DownLoadAppService extends Service {

    public static String downloadUpdateApkFilePath;//下载更新Apk 文件路径

    private NotificationManager mNotificationManager;
    private Notification.Builder notificationBuilder;
    private final int NOTIFICATION_ID = 100;
    private boolean downLoading;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("======","=====oncreate======");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!downLoading){
            String url = "http://down1.uc.cn/down2/zxl107821.uc/miaokun1/UCBrowser_V11.5.8.945_android_pf145_bi800_(Build170627172528).apk";
            String versionName = "912.83223.31";
            download(this,url,versionName);
        }


        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 让Service保持活跃,避免出现:
     * 如果启动此服务的前台Activity意外终止时Service出现的异常(也将意外终止)
     */
    private void starDownLoadForground() {
        CharSequence text = "下载中,请稍后...";
        notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);  // the status icon
        notificationBuilder.setTicker(text);  // the status text
        notificationBuilder.setWhen(System.currentTimeMillis());  // the time stamp
        notificationBuilder.setContentText(text);  // the contents of the entry
        notificationBuilder.setContentTitle("正在下载更新" + 0 + "%"); // the label of the entry
        notificationBuilder.setProgress(100, 0, false);
        notificationBuilder.setOngoing(true);
        notificationBuilder.setAutoCancel(true);
        Notification notification = notificationBuilder.getNotification();
        startForeground(NOTIFICATION_ID, notification);
    }


    public void download(final Context context, String url, final String serverVersionName) {

        if (mNotificationManager == null){

            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        starDownLoadForground();

        String packageName = context.getPackageName();
        String filePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//外部存储卡
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            Log.i("===", "没有SD卡");
            return;
        }

        final String apkLocalPath= filePath + File.separator + packageName + "_"+serverVersionName+".apk";

        downloadUpdateApkFilePath = apkLocalPath;

        downLoading = true;

        FileDownloader.setup(context);

        FileDownloader.getImpl().create(url)
                .setPath(apkLocalPath)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Toast.makeText(context, "下载出pending", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Log.d("========","======>:"+(soFarBytes*100.0/totalBytes));

                        int progress = (int) (soFarBytes*100.0/totalBytes);
                        notificationBuilder.setContentTitle("正在下载更新" + progress + "%"); // the label of the entry
                        notificationBuilder.setProgress(100, progress, false);
                        mNotificationManager.notify(NOTIFICATION_ID, notificationBuilder.getNotification());

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {

                        stopForeground(true);
                        mNotificationManager.cancel(NOTIFICATION_ID);
                        downLoading = false;
                        installApk(context,apkLocalPath);


                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Toast.makeText(context, "下载出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        Toast.makeText(context, "下载出warn", Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }


    private void installApk(Context context,String apkLocalPath){
        Intent i = new Intent(Intent.ACTION_VIEW);
        File apkFile = new File(apkLocalPath);
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
