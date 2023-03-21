package com.ansid.alertzm.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ansid.alertzm.Activities.MainActivity;
import com.ansid.alertzm.R;

import java.util.Random;

public class ParentService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Notification parentNotification = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID).setContentTitle("ALTRACK RUNNING")
                .setContentText("Looking for changes")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
        startForeground(new Random().nextInt(), parentNotification);
    }


    public ParentService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


}