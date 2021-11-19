package com.inaki.lifecycleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String NAME = "MainActivity";

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(NAME, "onCreate called");

        mButton = findViewById(R.id.button);
        mEditText = findViewById(R.id.edit_text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(NAME, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(NAME, "onResume called");

        mButton.setOnClickListener(v -> {

            Intent activityIntent = new Intent(getBaseContext(), MainActivity2.class);
            activityIntent.putExtra("DATA", String.valueOf(mEditText.getText()));

            startActivity(activityIntent);

            // Implicit intent example
            Uri webpage = Uri.parse("https://www.android.com");

            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

            // startActivity(webIntent);

        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(NAME, "onPause called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(NAME, "onRestart called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(NAME, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(NAME, "onDestroy called");
    }
}