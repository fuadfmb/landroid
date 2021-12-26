package com.orosoft.landroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class B5_ProgressBar extends AppCompatActivity {

    Button progress_hor, progress_ver;

    private TextView txtProgress;
    private ProgressBar progressBar,ringprogressBar, ringprogressBar2, verprogressbar;
    private int pStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b5_progressbar);

        progress_hor = (Button) findViewById(R.id.progress_hor);
        progress_ver = (Button) findViewById(R.id.progress_ver);
        //customprogress2 = (ProgressBar) findViewById(R.id.customprogress2);
        ringprogressBar = (ProgressBar) findViewById(R.id.ringprogressBar);
        verprogressbar = (ProgressBar) findViewById(R.id.verprogressBar);
        ringprogressBar2 = (ProgressBar) findViewById(R.id.ringprogressBar2);
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        ringprogressBar.setSecondaryProgress(100);
//        verprogressbar.setSecondaryProgress(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            ringprogressBar.setProgress(pStatus);
                            ringprogressBar.setSecondaryProgress(pStatus+5);
                            verprogressbar.setProgress(pStatus);
                            verprogressbar.setSecondaryProgress(pStatus+5);
                            ringprogressBar2.setProgress(pStatus);
                            ringprogressBar2.setSecondaryProgress(pStatus+5);

                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();




        progress_hor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pd = new ProgressDialog(B5_ProgressBar.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMessage("Horizontal progress");
                pd.setIndeterminate(true);
                pd.setCancelable(true);
                pd.show();
            }
        });


        progress_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pd = new ProgressDialog(B5_ProgressBar.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("Spinner progress");
                pd.setIndeterminate(true);
                pd.setCancelable(true);
                pd.show();
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
            intent.putExtra("file", "b5");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
