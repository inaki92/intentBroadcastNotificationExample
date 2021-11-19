package com.inaki.lifecycleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton2;

    private LocalBroadcastManager m;
    private MyBroadcast mBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextView = findViewById(R.id.textView);
        mButton2 = findViewById(R.id.button2);

        mBroadcast = new MyBroadcast();
        m = LocalBroadcastManager.getInstance(getBaseContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        m.registerReceiver(mBroadcast, MyBroadcast.INTENT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent oldIntent = getIntent();

        if (oldIntent != null) {
            if (oldIntent.getStringExtra("DATA") != null) {
                mTextView.setText(oldIntent.getStringExtra("DATA"));
            }
        }

        Intent mIntent = new Intent();
        mIntent.setAction(MyBroadcast.myAction);
        mIntent.putExtra(MyBroadcast.MY_EXTRA, "Received");

        createNotificationChannel();

        mButton2.setOnClickListener(v -> {
            m.sendBroadcast(mIntent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        m.unregisterReceiver(mBroadcast);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}