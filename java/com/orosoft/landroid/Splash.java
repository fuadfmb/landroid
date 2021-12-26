package com.orosoft.landroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Splash extends AppCompatActivity {
    public final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView myicon;
    SharedPreferences settings;
    TextView versiontxt;

    @Override
    protected void onCreate(Bundle sis){
        super.onCreate(sis);
        setContentView(R.layout.activity_splash);

        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean showsplash = settings.getBoolean("showsplash", false);

        versiontxt = (TextView) findViewById(R.id.versiontxt);
        versiontxt.setText( BuildConfig.VERSION_NAME );

        myicon = (ImageView) findViewById(R.id.myicon);
        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        myicon.startAnimation(anim1);

        if (showsplash){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        checkPermission();
                    }
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        else {
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }

    }

    public void onPause(){
        super.onPause();
        finish();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) !=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) !=
                        PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED) {//Can add more as per requirement
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WAKE_LOCK,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE},
                    123);
        }
    }

}