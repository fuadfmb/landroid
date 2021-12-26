package com.orosoft.landroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class B3_Switch extends AppCompatActivity {

    Switch myswitch;
    TextView tvswitch, tv_customswitch, tv_mat_switch;
    RadioGroup mat_toggle;
    SwitchCompat switch_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b3_switch);

        myswitch = (Switch) findViewById(R.id.switch1);
        tvswitch = (TextView) findViewById(R.id.tvswitch);
        mat_toggle = findViewById(R.id.mat_toggle);
        switch_custom = findViewById(R.id.switch_custom);
        tv_customswitch = findViewById(R.id.tv_customswitch);
        tv_mat_switch = findViewById(R.id.tv_mat_switch);

        tvswitch.setText("not checked");
        tv_mat_switch.setText("not checked");
        tv_customswitch.setText("not checked");

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvswitch.setText("checked");
                }
                else {
                    tvswitch.setText("not checked");
                }
            }
        });

        switch_custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tv_customswitch.setText("checked : true");
                }
                else{
                    tv_customswitch.setText("checked : false");
                }
            }
        });

        mat_toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.offers){
                    tv_mat_switch.setText("checked : offers");
                }
                else if (checkedId == R.id.search){
                    tv_mat_switch.setText("checked : search");
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
            intent.putExtra("file", "b3");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
