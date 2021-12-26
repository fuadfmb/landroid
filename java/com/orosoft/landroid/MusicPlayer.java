package com.orosoft.landroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MusicPlayer extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    ProgressBar progress_bar;
    ImageButton btnPlayPause, btnStop, btnloop;
    int finalTime, startTime;
    TextView now, total;
    Handler myhandler = new Handler();
    ImageView iconmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btnPlayPause = (ImageButton) findViewById(R.id.btnplaypause);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        now = (TextView) findViewById(R.id.currenttime);
        total = (TextView) findViewById(R.id.totaltime);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        iconmusic = (ImageView) findViewById(R.id.iconmusic);

        btnloop = (ImageButton) findViewById(R.id.btnloop);

        mediaPlayer = MediaPlayer.create(MusicPlayer.this, R.raw.kootai);



        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoominfinite);
        iconmusic.startAnimation(anim1);



        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        now.setText(Integer.toString(mediaPlayer.getCurrentPosition()/1000));
        now.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

        total.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
        progress_bar.setMax(finalTime);

        myhandler.postDelayed(updateSongTime, 100);


        progress_bar.setProgress( mediaPlayer.getCurrentPosition()/1000 );


        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    btnPlayPause.setBackgroundResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                }
                else {
                    btnPlayPause.setBackgroundResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayPause.setBackgroundResource(R.drawable.ic_play);
                mediaPlayer.reset();
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(MusicPlayer.this, R.raw.kootai);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if ( ! mediaPlayer.isLooping()){
                    //Toast.makeText(MusicPlayer.this, "done playing.", Toast.LENGTH_SHORT).show();
                    btnPlayPause.setBackgroundResource(R.drawable.ic_play);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicPlayer.this, R.raw.kootai);
                }

            }
        });

        btnloop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isLooping()){
                    mediaPlayer.setLooping(false);
                    btnloop.setBackgroundResource(R.drawable.ic_loopoff);
                    Toast.makeText(MusicPlayer.this, "looping is off", Toast.LENGTH_SHORT).show();
                }
                else{
                    mediaPlayer.setLooping(true);
                    btnloop.setBackgroundResource(R.drawable.ic_loopon);
                    Toast.makeText(MusicPlayer.this, "looping is on", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    Runnable updateSongTime = new Runnable() {
        @Override
        public void run() {
            //now.setText(Integer.toString(mediaPlayer.getCurrentPosition()/1000));
            now.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) mediaPlayer.getCurrentPosition()),
                    TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getCurrentPosition()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) mediaPlayer.getCurrentPosition()))));
            myhandler.postDelayed(this, 100);
            progress_bar.setProgress(mediaPlayer.getCurrentPosition());
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        finish();
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
            intent.putExtra("file", "ex1");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}