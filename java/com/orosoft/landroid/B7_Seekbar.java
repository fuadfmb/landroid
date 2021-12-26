package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class B7_Seekbar extends AppCompatActivity {

    TextView tv_seekbar,seek_status;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b7_seekbar);

        tv_seekbar = (TextView) findViewById(R.id.tv_seekbar);
        seek_status = (TextView) findViewById(R.id.seek_status);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        tv_seekbar.setText("0%");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_seekbar.setText( progress + "%" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seek_status.setText("You started dragging!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seek_status.setText("You released it!");
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
            intent.putExtra("file", "b7");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}