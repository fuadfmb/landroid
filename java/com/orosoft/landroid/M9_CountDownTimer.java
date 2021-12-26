package com.orosoft.landroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class M9_CountDownTimer extends AppCompatActivity {

    ConstraintLayout cont2;
    TextView countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m9_countdownt);

        countdown = (TextView) findViewById(R.id.countdown);
        cont2 = (ConstraintLayout) findViewById(R.id.cont2);

        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished <= 3000){
                    // 3 seconds left...
                }
                countdown.setText(String.format(Locale.getDefault(), "%d sec.", millisUntilFinished / 1000L));
            }
            public void onFinish() {
                cont2.setBackgroundColor(Color.parseColor("#ff0000"));
                countdown.setText("Done.");
            }
        }.start();

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
            intent.putExtra("file", "m9");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}