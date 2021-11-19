package com.inaki.lifecycleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcast extends BroadcastReceiver {

    static String MY_EXTRA = "MY_EXTRA";
    static String myAction = "MY_BROADCAST";

    static IntentFilter INTENT = new IntentFilter(myAction);

    @Override
    public void onReceive(Context context, Intent intent) {
        String received = intent.getStringExtra(MY_EXTRA);
        Toast.makeText(context, "Notification sent", Toast.LENGTH_LONG).show();
        Log.d("MyBroadcast", "BroadcastReceived: " + received);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(432, createNotification(context).build());
    }

    private NotificationCompat.Builder createNotification(Context context) {
        return new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("This is my notification")
                .setContentText("Notification requested")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
