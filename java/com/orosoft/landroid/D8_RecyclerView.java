package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class D8_RecyclerView extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d8_recyclerv);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        MyAdapter adapter = new MyAdapter(this, getData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<RecyclerParser> getData(){
        String[] Countries = {
                "Ethiopia", "Kenya", "United kingdom", "Brazil", "UAE (Dubai)",
                "Qatar", "Japan", "Egypt", "India", "Morocco", "Nigeria",
                "Libya"
        };
        String[] capitals  = {
                "Addis ababa/Finfinne", "Nairobi", "London", "Rio de jeneiro",
                "Abu dhabi", "Doha", "Tokyo", "Cairo", "New Delhi", "Rabat", "Abuja",
                "Tripoli"
        };

        List<RecyclerParser> data = new ArrayList<>();

        for (int i=0; i<Countries.length; i++){
            RecyclerParser countrydata = new RecyclerParser();
            countrydata.country = Countries[i];
            countrydata.capital = capitals[i];
            data.add(countrydata);
        }
        return data;
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
            intent.putExtra("file", "d8");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
