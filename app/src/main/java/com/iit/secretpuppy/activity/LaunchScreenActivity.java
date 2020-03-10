package com.iit.secretpuppy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import com.iit.secretpuppy.R;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        //load dog running gif
        WebView wv = findViewById(R.id.webview1);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.loadUrl("file:///android_asset/sample.html");

        //Sound Clip play for don running animation
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


