package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        WebView wv = findViewById(R.id.webview1);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);

        wv.loadUrl("file:///android_asset/sample.html");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.walk);
        mp.start();
        mp.setLooping(true);
        int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                mp.stop();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, secondsDelayed * 1000);
    }
}


