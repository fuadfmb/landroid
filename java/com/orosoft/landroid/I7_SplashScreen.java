package com.orosoft.landroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class I7_SplashScreen extends AppCompatActivity {

    Switch myswitch;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i7_splashscreen);

        myswitch = (Switch) findViewById(R.id.myswitch);
        // Get the saved flag (or default value if it hasn't been saved yet)
        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        boolean ispermitted = settings.getBoolean("showsplash", false);
        if (ispermitted){
            myswitch.setChecked(true);
        }
        else{
            myswitch.setChecked(false);
        }


        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    editor.putBoolean("showsplash", true).apply();
                }
                else{
                    editor.putBoolean("showsplash", false).apply();
                }

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
            intent.putExtra("file", "i7");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}