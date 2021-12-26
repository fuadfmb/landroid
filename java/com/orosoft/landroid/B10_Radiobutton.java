package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class B10_Radiobutton extends AppCompatActivity {

    TextView tv_rg;
    RadioGroup myradiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b10_radiobutton);

        myradiogroup = (RadioGroup) findViewById(R.id.myradiogroup);
        tv_rg = (TextView) findViewById(R.id.tv_rg);
        tv_rg.setText("You have selected : ");

        myradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){
                    case R.id.ch1:
                        tv_rg.setText("You have selected : choice 1");
                        break;
                    case R.id.ch2:
                        tv_rg.setText("You have selected : choice 2");
                        break;
                    case R.id.ch3:
                        tv_rg.setText("You have selected : choice 3");
                        break;
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
            intent.putExtra("file", "b10");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}