package com.orosoft.landroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class I3_Email extends AppCompatActivity {

    EditText emailaddress, messagearea, emailsubject;
    Button sendemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i3_email);

        emailaddress = (EditText) findViewById(R.id.emailarea);
        messagearea = (EditText) findViewById(R.id.messagearea);
        sendemail = (Button) findViewById(R.id.sendemail);
        emailsubject = (EditText) findViewById(R.id.emailsubject);

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailto = emailaddress.getText().toString();
                String message = messagearea.getText().toString();
                String subject = emailsubject.getText().toString();

                boolean isvalid = mailto.contains("@") && mailto.contains(".") && mailto.length() != 0 && message.length() != 0 && subject.length() != 0;

                if ( isvalid ){
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"+mailto));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Complete action using"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(I3_Email.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(I3_Email.this, "Please fill all required fields accordingly!", Toast.LENGTH_SHORT).show();
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
            intent.putExtra("file", "i3");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}