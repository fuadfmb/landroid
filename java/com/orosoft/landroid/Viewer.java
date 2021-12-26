package com.orosoft.landroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import static com.google.android.gms.ads.MobileAds.initialize;

public class Viewer extends AppCompatActivity {

    RadioButton java, xml, others;
    WebView web;
    String filename;
    RadioGroup radioGload;
    private InterstitialAd mInterstitialAd;
    AdView adView_viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        filename = extras.getString("file");

        // initialize...
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //getSupportActionBar().setTitle( "Source : " + filename );

        web = (WebView) findViewById(R.id.web);
        radioGload = findViewById(R.id.rg_load);
        java = findViewById(R.id.java);
        xml = findViewById(R.id.xml);
        others = findViewById(R.id.others);

        java.setChecked(true);

        AdRequest adRequest = new AdRequest.Builder().build();

        // real : ca-app-pub-1500047410140952/8904478417
        // test : ca-app-pub-3940256099942544/1033173712
        InterstitialAd.load(this,getString(R.string.real_interstitial), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("msg", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAG", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });


        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setTextZoom(60);

        // load speed
        web.setLayerType(View.LAYER_TYPE_SOFTWARE, null);



        AdView mAdView = findViewById(R.id.adView_viewer);
        AdRequest adRequestbanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestbanner);




        delay();
        web.loadUrl("file:///android_asset/java/"+filename+".html" );

        radioGload.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if ( checkedId == R.id.java ){
                    delay();
                    web.loadUrl( "file:///android_asset/java/"+filename+".html" );
                }
                else if ( checkedId == R.id.xml ){
                    delay();
                    web.loadUrl( "file:///android_asset/xml/"+filename+".html" );
                }
                else if ( checkedId == R.id.others ){
                    delay();
                    web.loadUrl( "file:///android_asset/more/"+filename+".html"  );
                }
            }
        });

    }

    private void delay() {
        final Dialog mDialog = new Dialog(Viewer.this, R.style.NewDialog);
        mDialog.addContentView(
                new ProgressBar(Viewer.this),
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        mDialog.setCancelable(false);
        mDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
            }
        }, 500);
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null){
            finish();
            mInterstitialAd.show(Viewer.this);
        }
        else{
            finish();
        }
    }
}
