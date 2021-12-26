package com.orosoft.landroid;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class I2_SMS extends AppCompatActivity {

    EditText phonearea, messagearea;
    Button sendsms;

    int REQUEST_SEND_SMS = 123456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i2_sms);

        phonearea = (EditText) findViewById(R.id.phonearea);
        messagearea = (EditText) findViewById(R.id.messagearea);
        sendsms = (Button)findViewById(R.id.sendsms);

//        sendsms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String usermessage = messagearea.getText().toString();
//                String userphone = phonearea.getText().toString();
//
//                if (userphone.length() >= 10 && !usermessage.equals("")){
//                    try {
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage(userphone, null, usermessage, null, null);
//                        Toast.makeText(getApplicationContext(), "Message Sent.", Toast.LENGTH_LONG).show();
//                    } catch (Exception e) {
//                        Toast.makeText(getApplicationContext(), "Please Grant permission and try again!" , Toast.LENGTH_SHORT).show();
//
//                        // request permission...
//                        String[] permissions = { Manifest.permission.SEND_SMS };
//                        ActivityCompat.requestPermissions(I2_SMS.this, permissions, REQUEST_SEND_SMS);
//                    }
//                }else
//                    Toast.makeText(I2_SMS.this, "Fill all required fields properly...", Toast.LENGTH_SHORT).show();
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
            intent.putExtra("file", "i2");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}