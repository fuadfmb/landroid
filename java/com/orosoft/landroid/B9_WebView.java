package com.orosoft.landroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class B9_WebView extends AppCompatActivity {

    ImageButton back, refresh, forward, search;
    Button local;
    WebView mywebview;
    EditText urlbox;
    ProgressBar webProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b9_webview);

        back = (ImageButton) findViewById(R.id.back);
        refresh = (ImageButton) findViewById(R.id.refresh);
        forward = (ImageButton) findViewById(R.id.forward);
        local = (Button) findViewById(R.id.local);
        search = (ImageButton) findViewById(R.id.search);
        urlbox = (EditText) findViewById(R.id.urlbox);
        webProgress = (ProgressBar) findViewById(R.id.webProgress);

        mywebview = (WebView) findViewById(R.id.mywebview);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setSupportZoom(true);
        mywebview.getSettings().setBuiltInZoomControls(true);
        mywebview.loadUrl("https://www.google.com");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mywebview.canGoBack()){
                    mywebview.goBack();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mywebview.reload();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mywebview.canGoForward()){
                    mywebview.goForward();
                }
            }
        });

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mywebview.loadUrl("file:///android_asset/localfile.html");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = urlbox.getText().toString().replace(" ","").trim();

                if (url.equals("")){
                    mywebview.loadUrl("https://www.google.com");
                }
                else if ( url.contains(".") ){
                    mywebview.loadUrl( "https://" + Uri.encode(url) );
                }
                else{
                    mywebview.loadUrl("https://www.google.com/search?q=" + Uri.encode(url) );
                }

            }
        });

        mywebview.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                //mywebview.loadUrl("file:///android_asset/errorpage.html"); you can load error page...

                String MyPage = "<html><body><h1>An error occured !</h1><b>Description :</b><br><font color=\"red\">"+description+"</font>" +
                        "<br><b>failing Url :</b><br><font color=\"red\">"+failingUrl+"</font></body></html>";
                mywebview.loadData(MyPage, "text/html", "utf-8" );

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webProgress.setVisibility(View.INVISIBLE);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.show_source){
            Intent intent = new Intent(getApplicationContext(), Viewer.class);
            intent.putExtra("file", "b9");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}