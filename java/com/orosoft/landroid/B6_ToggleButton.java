package com.orosoft.landroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class B6_ToggleButton extends AppCompatActivity {

    ToggleButton toggleButton;
    ConstraintLayout toggle_cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b6_togglebutton);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggle_cont = findViewById(R.id.toggle_cont);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggle_cont.setBackgroundColor(Color.parseColor("#ffea00"));
                else
                    toggle_cont.setBackgroundResource(R.drawable.backbg);
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
            intent.putExtra("file", "b6");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}