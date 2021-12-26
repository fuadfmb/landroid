package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class B8_Spinner extends AppCompatActivity {

    Spinner spinner;
    TextView spin_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b8_spinner);

        spinner = (Spinner) findViewById(R.id.spinner);
        spin_status = (TextView) findViewById(R.id.spin_status);
        spin_status.setText("checked item : " );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        spin_status.setText( "checked item : item 1" );
                        break;
                    case 1:
                        spin_status.setText( "checked item : item 2" );
                        break;
                    case 2:
                        spin_status.setText( "checked item : item 3" );
                        break;
                    case 3:
                        spin_status.setText( "checked item : item 4" );
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            intent.putExtra("file", "b8");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}