package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class D7_BottomSheet extends AppCompatActivity {

    Button btnsheet1, btnsheet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d7_bottomsheet);

        btnsheet1 = (Button) findViewById(R.id.btnsheet1);
        btnsheet2 = (Button) findViewById(R.id.btnsheet2);


        btnsheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new BottomSheetDialog
                BottomSheetDialog dialog = new BottomSheetDialog(D7_BottomSheet.this);
                //Inflate the layout R.layout.my_dialog_layout
                dialog.setContentView(R.layout.bottomsheet2);
                Button btn_bsheet_okay = dialog.findViewById(R.id.btn_bsheet_okay);
                btn_bsheet_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(D7_BottomSheet.this, "You clicked okay button", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                //Show the dialog
                dialog.show();
            }
        });

        btnsheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_BottomSheet b = new Frag_BottomSheet();
                b.show(getSupportFragmentManager(), b.getTag());
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
            intent.putExtra("file", "d7");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
