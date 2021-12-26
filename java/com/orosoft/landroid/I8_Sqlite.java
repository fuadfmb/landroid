package com.orosoft.landroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class I8_Sqlite extends AppCompatActivity {

    Button btnsave,btnclear;
    EditText etNewData;
    SQLiteDatabase mydatabase;
    String entriesText = "";
    TextView tventries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i8_sqlite);

        btnsave = (Button) findViewById(R.id.btnsave);
        etNewData = (EditText) findViewById(R.id.etyourname);
        tventries = (TextView) findViewById(R.id.entries);
        btnclear = (Button) findViewById(R.id.btnclear);

        //create table IF NOT EXISTS!
        mydatabase = openOrCreateDatabase("exampledb",MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS exampletable (name VARCHAR);");

        //mirror all entries to string array
        Cursor cursor = mydatabase.rawQuery("Select * from exampletable",null);
        cursor.moveToFirst();
        //append first item !!
        if (cursor.getCount() != 0) entriesText += cursor.getString(0) + "\n";
        //prepare a list of string for TVentries
        while (cursor.moveToNext()){
            entriesText += cursor.getString(0) + "\n";
        }
        tventries.setText(entriesText);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etNewData.getText().toString();
                if (name.length() != 0){

                    String stmt = String.format("INSERT INTO exampletable VALUES('%s');", name);
                    mydatabase.execSQL( stmt );
                    I8_Sqlite.this.recreate();

                }
                else{
                    Toast.makeText(I8_Sqlite.this, "type something please...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stmt = "DELETE FROM exampletable WHERE 1";
                mydatabase.execSQL( stmt );
                I8_Sqlite.this.recreate();
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
            intent.putExtra("file", "i8");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}