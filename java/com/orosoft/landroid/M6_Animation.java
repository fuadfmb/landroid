package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class M6_Animation extends AppCompatActivity {

    Button animate1, animate2, animate3, animate4, animate5, animate6, animate7, animate8, animate9, animate10;
    ImageView imageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m6_anim);

        animate1 = (Button) findViewById(R.id.top);
        animate2 = (Button) findViewById(R.id.left);
        animate3 = (Button) findViewById(R.id.bottom);
        animate4 = (Button) findViewById(R.id.right);
        animate5 = (Button) findViewById(R.id.shake);
        animate6 = (Button) findViewById(R.id.fadein);
        animate7 = (Button) findViewById(R.id.fadeout);
        animate8 = (Button) findViewById(R.id.bounce);
        animate9 = (Button) findViewById(R.id.clockwise);
        animate10 = (Button) findViewById(R.id.zoom);

        imageV = (ImageView) findViewById(R.id.imageV);

        Animation a1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        Animation a2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        animate1.startAnimation(a1);
        animate2.startAnimation(a2);
        animate3.startAnimation(a1);
        animate4.startAnimation(a2);
        animate5.startAnimation(a1);
        animate6.startAnimation(a2);
        animate7.startAnimation(a1);
        animate8.startAnimation(a2);
        animate9.startAnimation(a1);
        animate10.startAnimation(a2);


        animate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
                imageV.startAnimation(anim1);
            }
        });

        animate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
                imageV.startAnimation(anim1);
            }
        });

        animate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
                imageV.startAnimation(anim1);
            }
        });

        animate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                imageV.startAnimation(anim1);
            }
        });

        animate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                imageV.startAnimation(anim1);
            }
        });

        animate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                imageV.startAnimation(anim2);
            }
        });
        animate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                imageV.startAnimation(anim3);
            }
        });
        animate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                imageV.startAnimation(anim4);
            }
        });
        animate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
                imageV.startAnimation(anim5);
            }
        });
        animate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                imageV.startAnimation(anim6);
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
            intent.putExtra("file", "m6");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}