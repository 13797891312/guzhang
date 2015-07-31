package com.caiyun.guzhang.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.zhaojin.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2015/7/27.
 */
public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: ");
            LogUtils.e("jpush", printBundle(bundle));
            show_normal(context,bundle);

        }else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            sb.append(key + "---------" + bundle.getString(key));
            sb.append("\n");
        }
        return sb.toString();
    }


    public void show_normal(Context context, Bundle bundle) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    context).setSmallIcon(R.drawable.ic_luncher)// 设置图标
                    .setContentTitle(bundle.getString("cn.jpush.android.TITLE"))// 设置标题
                    .setContentText(bundle.getString("cn.jpush.android.MESSAGE"));// 设置内容
			/* 设置PendingIntent，当用户点击通知跳转到另一个界面，当退出该界面，直接回到HOME */
            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    context, 1, new Intent(context,MainActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);// 设置PendingIntent
            // 创建NotificationManager 对象
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = mBuilder.build();// 生成Notification对象
            notification.flags = Notification.FLAG_AUTO_CANCEL;// 点击后自动关闭通知
            notification.defaults = Notification.DEFAULT_VIBRATE
                    | Notification.DEFAULT_SOUND;
            notification.tickerText = bundle.getString("cn.jpush.android.TITLE");
            mNotificationManager.notify(1, notification);
    }
}
