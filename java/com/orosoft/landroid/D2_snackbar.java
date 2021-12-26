package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class D2_snackbar extends AppCompatActivity {

    Button snack, snack2;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2_snackbar);

        snack = (Button) findViewById(R.id.snackbar);
        container = (ConstraintLayout) findViewById(R.id.container);
        snack2 = (Button) findViewById(R.id.snack2);

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(container, "This is me, Snackbar!", Snackbar.LENGTH_LONG).show();

            }
        });

        snack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(container, "click the action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(D2_snackbar.this, "You clicked action button !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

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
            intent.putExtra("file", "d2");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
