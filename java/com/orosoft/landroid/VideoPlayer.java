package com.orosoft.landroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayer extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setOnCompletionListener(this);

        String pathUri = "android.resource://" + getPackageName() + "/" + R.raw.video04;
        Uri uri = Uri.parse(pathUri);
        videoView.setVideoURI(uri);
        videoView.start();
        // video started playing...

    }

    private void setVideo(int vidID) {


    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(VideoPlayer.this);
        builder.setTitle("Playback Finished")
                .setIcon(R.drawable.ic_double_arrow)
                .setMessage("Do you want to replay this video?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == -1){
                            videoView.seekTo(0);
                            videoView.start();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
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
            intent.putExtra("file", "ex2");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}












