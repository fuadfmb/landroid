package com.orosoft.landroid;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.R.attr.x;
import static android.R.attr.y;

public class M7_CustomFonts extends AppCompatActivity {

    EditText fontET;
    TextView tv6, tv7;
    private Typeface myFont1, myFont2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m7_customfonts);

        fontET = (EditText) findViewById(R.id.fontET);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);

        myFont1 = Typeface.createFromAsset(M7_CustomFonts.this.getAssets(), "Sofia_Regular.ttf");
        myFont2 = Typeface.createFromAsset(M7_CustomFonts.this.getAssets(), "Digital_tech.otf");

        tv6.setTypeface(myFont1);
        tv7.setTypeface(myFont2);

        fontET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv6.setText(fontET.getText().toString());
                tv7.setText(fontET.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
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
            intent.putExtra("file", "m7");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}