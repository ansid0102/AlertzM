package com.ansid.alertzm.Services;

import static com.ansid.alertzm.Activities.MainActivity.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ansid.alertzm.R;

import java.util.Random;

public class ClipboardMonitorService extends Service {
    public static final String CLIP_CHANNEL_ID="CLIPBOARD NOTIFICATION";
    NotificationManager clipboardInstanceManager = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Notification createClipboardNotification(){
        return new NotificationCompat.Builder(this,CLIP_CHANNEL_ID)
                .setContentTitle("Clipboard Change Detected!!")
                .setContentText("Would you like us to remind you about this?")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();
    }

    @Override
    public void onCreate(){
        clipboardInstanceManager = createClipboardNotificationChannel();
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        if(clipboardInstanceManager==null){
            onCreate();
        }
        clipboardInstanceManager.notify(new Random().nextInt(),createClipboardNotification());
        return START_STICKY;
    }
    public NotificationManager createClipboardNotificationChannel(){
        NotificationManager clipboardNotificationManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel clipboardNotificationChannel = new NotificationChannel(CLIP_CHANNEL_ID, "PARENT ALTRAK", NotificationManager.IMPORTANCE_HIGH);
            clipboardNotificationManager = getSystemService(NotificationManager.class);
            clipboardNotificationManager.createNotificationChannel(clipboardNotificationChannel);
        }
        return clipboardNotificationManager;
    }
}
