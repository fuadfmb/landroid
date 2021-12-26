package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class D5_TextInputLayout extends AppCompatActivity {

    TextInputLayout tiluser, tilpass;
    EditText ed1, ed2;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d5_textinputl);

        tiluser = (TextInputLayout) findViewById(R.id.tiluser);
        tilpass = (TextInputLayout) findViewById(R.id.tilpass);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);


        login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ed1.getText()) || TextUtils.isEmpty(ed2.getText())) {
                    if (TextUtils.isEmpty(ed1.getText())) {
                        tiluser.setError("This field needs to be filled!");
                    }
                    else{
                        tiluser.setErrorEnabled(false); //ignore error if not empty
                    }
                    if (TextUtils.isEmpty(ed2.getText())) {
                        tilpass.setError("This field needs to be filled!");
                    }
                    else{
                        tilpass.setErrorEnabled(false); //ignore error if not empty
                    }
                }
                else {
                    Toast.makeText(D5_TextInputLayout.this, "no error", Toast.LENGTH_SHORT).show();
                    tiluser.setErrorEnabled(false); //ignore error if not empty
                    tilpass.setErrorEnabled(false);
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
            intent.putExtra("file", "d5");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
