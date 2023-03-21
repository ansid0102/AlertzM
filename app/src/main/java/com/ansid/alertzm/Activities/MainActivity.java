package com.ansid.alertzm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ansid.alertzm.R;
import com.ansid.alertzm.Services.ClipboardMonitorService;
import com.ansid.alertzm.Services.ParentService;

public class MainActivity extends AppCompatActivity {
    Button trackActivitiesButton;
    public static final String CHANNEL_ID = "ALTRACK CHANNEL";
    Button setTrackersButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trackActivitiesButton = (Button)  findViewById(R.id.TrackActivities);
        setTrackersButton = (Button) findViewById(R.id.SetTrackers);

        trackActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openTrackActivitiesActivity = new Intent(MainActivity.this,TrackActivities.class);
                startActivity(openTrackActivitiesActivity);
            }
        });
        setTrackersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSetTrackerActivity = new Intent(MainActivity.this,SetTrackers.class);
                startActivity(openSetTrackerActivity);
            }
        });
        Intent parentService = new Intent(this, ParentService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(parentService);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                Toast.makeText(getApplicationContext(),"CLIPBOARD WORKS",Toast.LENGTH_LONG).show();
                startService(new Intent(MainActivity.this, ClipboardMonitorService.class));
            }
        });
    }
}