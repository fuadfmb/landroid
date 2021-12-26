package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class M5_SwipeToRefresh extends AppCompatActivity {

    //compile 'com.android.support:support-core-ui:24.2.0'

    TextView text_anime;
    private Handler handler = new Handler();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m5_swipetorefresh);

        text_anime = (TextView) findViewById(R.id.text_anime);
        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources( android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mSwipeRefreshLayout.setRefreshing(false);

                //text Animation
                final String message = "Lorem ipsum dolor sit amet...";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for ( i=0; i<message.length(); i++) {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    text_anime.setText( message.substring(0,i+1) );
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

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
            intent.putExtra("file", "m5");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}