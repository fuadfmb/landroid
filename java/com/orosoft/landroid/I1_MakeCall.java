package com.orosoft.landroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class I1_MakeCall extends AppCompatActivity {

    Button makecall;
    EditText phoneNumArea;
    int REQUEST_PHONE_CALL = 121314;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i1_call);

        makecall = (Button) findViewById(R.id.makecall);
        phoneNumArea = (EditText) findViewById(R.id.emailarea);

//        makecall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phonenumber = phoneNumArea.getText().toString();
//                String finalPhone = Uri.encode( phonenumber );
//
//                if (phonenumber.length() >= 3){
//                    try {
//                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
//                            Toast.makeText(getApplicationContext(), "Please Grant permission and try again!" , Toast.LENGTH_SHORT).show();
//
//                            // request permission...
//                            String[] permissions = { Manifest.permission.CALL_PHONE };
//                            ActivityCompat.requestPermissions(I1_MakeCall.this, permissions, REQUEST_PHONE_CALL);
//
//                        }
//                        else {
//                            Intent intent = new Intent(Intent.ACTION_CALL);
//                            intent.setData( Uri.parse("tel:" + finalPhone ) );
//                            startActivity(intent);
//                        }
//                    }
//                    catch (Exception e) {
//                        Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    Toast.makeText(I1_MakeCall.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
//                }
//
//
//
//            }
//        });

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
            intent.putExtra("file", "i1");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}