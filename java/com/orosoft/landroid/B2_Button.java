package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class B2_Button extends AppCompatActivity {

    Button mybutton, materialbtn, custombtn;
    TextView click, longclick;
    int clickCount = 0, longClickCount = 0;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_button);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mybutton = (Button) findViewById(R.id.mybutton);
        click = (TextView) findViewById(R.id.click);
        longclick = (TextView) findViewById(R.id.longclick);
        custombtn = (Button) findViewById(R.id.btn3);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        materialbtn = findViewById(R.id.materialbtn);


        click.setText( "single click : " + clickCount );
        longclick.setText( "long click : " + longClickCount );

        View.OnClickListener mClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                click.setText( "single click : " + clickCount );
            }
        };
        View.OnLongClickListener mLongClick = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickCount++;
                longclick.setText( "long click : " + longClickCount );
                return true;
            }
        };

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        mybutton.startAnimation(anim1);
        custombtn.startAnimation(anim1);
        imageButton.startAnimation(anim3);
        materialbtn.startAnimation(anim1);

        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                click.setText( "single click : " + clickCount );
            }
        });

        mybutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickCount++;
                longclick.setText( "long click : " + longClickCount );
                return true;
            }
        });

        custombtn.setOnClickListener(mClick);
        custombtn.setOnLongClickListener(mLongClick);
        imageButton.setOnClickListener(mClick);
        imageButton.setOnLongClickListener(mLongClick);
        materialbtn.setOnClickListener(mClick);
        materialbtn.setOnLongClickListener(mLongClick);


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
            intent.putExtra("file", "b2");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
