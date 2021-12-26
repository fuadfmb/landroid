package com.orosoft.landroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class M1_AlertDialog extends AppCompatActivity {

    Button alert1, alert2, alert3, alert4, alert5, alert6, alert7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_alert);

        alert1 = (Button) findViewById(R.id.alert1);
        alert2 = (Button) findViewById(R.id.alert2);
        alert3 = (Button) findViewById(R.id.alert3);
        alert4 = (Button) findViewById(R.id.alert4);
        alert5 = (Button) findViewById(R.id.alert5);
        alert6 = (Button) findViewById(R.id.alert6);
        alert7 = (Button) findViewById(R.id.alert7);


        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        alert1.startAnimation(anim1);
        alert2.startAnimation(anim2);
        alert3.startAnimation(anim1);
        alert4.startAnimation(anim2);
        alert5.startAnimation(anim1);
        alert6.startAnimation(anim2);
        alert7.startAnimation(anim1);

        alert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(M1_AlertDialog.this);
                builder.setTitle("Alert Dialog")
                .setMessage("Description/message goes here..." )
                .setIcon(R.drawable.info_black)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(M1_AlertDialog.this, "you clicked Ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(M1_AlertDialog.this, "you clicked cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        alert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(M1_AlertDialog.this);
                builder.setTitle("Choose any item");
                final List<String> lables = new ArrayList<>();
                lables.add("Item 1");
                lables.add("Item 2");
                lables.add("Item 3");
                lables.add("Item 4");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(M1_AlertDialog.this, android.R.layout.simple_list_item_1, lables);
                builder.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(M1_AlertDialog.this,"You have selected " + lables.get(which),Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        alert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(M1_AlertDialog.this);
                View promptsView = li.inflate(R.layout.alert_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(M1_AlertDialog.this);
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.etUserInput);
                alertDialogBuilder.setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String x = userInput.getText().toString();
                        if (x.length() == 0){
                            Toast.makeText(M1_AlertDialog.this, "You clicked Ok", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(M1_AlertDialog.this, "Entered: " + userInput.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(M1_AlertDialog.this, "You clicked Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        alert4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog mDialog = new Dialog(M1_AlertDialog.this, R.style.AppBaseTheme);
                mDialog.setContentView(R.layout.w_fullscreen);
                mDialog.show();
            }
        });

        alert5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog d = new Dialog(M1_AlertDialog.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d.setContentView(R.layout.custom_alert);
                d.show();

            }
        });

        alert6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(M1_AlertDialog.this);
                View view = getLayoutInflater().inflate(R.layout.custom_alert2, null);
                builder.setView(view);
                final AlertDialog d = builder.create();
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d.show();

                Button yes = (Button) view.findViewById(R.id.btnyes);
                ImageButton cancel = (ImageButton) view.findViewById(R.id.btncancel);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                        Toast.makeText(M1_AlertDialog.this, "You accepted the terms!", Toast.LENGTH_SHORT).show();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                        Toast.makeText(M1_AlertDialog.this, "You clicked exit!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        alert7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder( M1_AlertDialog.this );
                AlertDialog dialog = builder.create();
                dialog.setTitle("Animated Dialog");
                dialog.setMessage("Description goes here...");
                dialog.setCancelable(true);
                dialog.getWindow().getAttributes().windowAnimations = R.style.alertDialog;
                dialog.show();
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

        if (item.getItemId() == R.id.show_source) {
            Intent intent = new Intent(getApplicationContext(), Viewer.class);
            intent.putExtra("file", "m1");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}