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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class B4_CheckBox extends AppCompatActivity {

    TextView checkbox_state, tv_square, tv_round, tv_star, tv_cancel;
    CheckBox checkBox;
    AppCompatCheckBox check_square, check_round, check_star, check_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4_checkbox);

        checkbox_state = (TextView) findViewById(R.id.checkbox_state);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        tv_square = findViewById(R.id.tv_check_square);
        tv_round = findViewById(R.id.tv_check_round);
        tv_star = findViewById(R.id.tv_check_star);
        tv_cancel = findViewById(R.id.tv_check_cancel);

        check_square = findViewById(R.id.check_square);
        check_round = findViewById(R.id.check_round);
        check_star = findViewById(R.id.check_star);
        check_cancel = findViewById(R.id.check_cancel);

        checkbox_state.setText("is not checked");

        // normal checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkbox_state.setText("is checked");
                }
                else {
                    checkbox_state.setText("is not checked");
                }
            }
        });
        // square checkbox
        check_square.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tv_square.setText("checked");
                }
                else{
                    tv_square.setText("not checked");
                }
            }
        });

        // round checkbox
        check_round.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tv_round.setText("checked");
                }
                else{
                    tv_round.setText("not checked");
                }
            }
        });

        // star checkbox
        check_star.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tv_star.setText("checked");
                }
                else{
                    tv_star.setText("not checked");
                }
            }
        });

        // cancel checkbox
        check_cancel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tv_cancel.setText("checked");
                }
                else{
                    tv_cancel.setText("not checked");
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
            intent.putExtra("file", "b4");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}

