package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class M2_ListView extends AppCompatActivity {

    ListView mylist;
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
        setContentView(R.layout.activity_m2_listview);

        mylist = (ListView) findViewById(R.id.mylist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, footballClubs);
        mylist.setAdapter(adapter);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(M2_ListView.this, "You clicked : " + footballClubs[position] , Toast.LENGTH_SHORT).show();
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
            intent.putExtra("file", "m2");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}