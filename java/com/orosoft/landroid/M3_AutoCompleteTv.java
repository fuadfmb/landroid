package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class M3_AutoCompleteTv extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiautoCompleteTextView;

    String[] footballClubs = {
            "Real Madrid",
            "Barcelona",
            "Manchester United",
            "Chelsea FC",
            "Bayern Munich",
            "Arsenal FC",
            "Paris Saint Germain",
            "Juventus",
            "Liverpool FC",
            "Manchester City",
            "Borussia Dortmund",
            "Atletico Madrid",
            "Tottenham Hotspur",
            "Inter Milan",
            "Leicester City",
            "Napoli",
            "Southampton FC",
            "Everton",
            "Westham united",
            "Schalke 04",
            "AC Milan",
            "Galatasaray",
            "Al-Ahly",
            "Al-Hilal Omdurman",
            "Getafe FC",
            "Swansea",
            "New Castle United"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3_autocompletetv);


        autoCompleteTextView  = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        multiautoCompleteTextView = findViewById(R.id.multiactv);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item, footballClubs);
        // for single actv...
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        // for multi  actv...
        multiautoCompleteTextView.setThreshold(1);
        multiautoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiautoCompleteTextView.setAdapter(adapter);

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
            intent.putExtra("file", "m3");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}