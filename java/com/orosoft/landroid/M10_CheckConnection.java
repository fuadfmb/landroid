package com.orosoft.landroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class M10_CheckConnection extends AppCompatActivity {

    Button check_conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m10_checkconn);

        check_conn = (Button) findViewById(R.id.check_conn);
        check_conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnectedNetwork(M10_CheckConnection.this)){
                    Toast.makeText(M10_CheckConnection.this, "Connected!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(M10_CheckConnection.this, "Not connected!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public static boolean isConnectedNetwork (Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo () != null && cm.getActiveNetworkInfo().isConnectedOrConnecting ();
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
            intent.putExtra("file", "m10");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}