package com.orosoft.landroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    LayoutInflater li;
    List<RecyclerParser> data = Collections.emptyList();

    public MyAdapter(Context context, List<RecyclerParser> myData){
        li = LayoutInflater.from(context);
        data = myData;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.recycler_single_row, parent, false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        RecyclerParser p = data.get(position);
        holder.capital.setText(p.capital);
        holder.country.setText(p.country);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView country;
        TextView capital;
        public MyHolder(View itemView){
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.country);
            capital = (TextView) itemView.findViewById(R.id.capital);

        }
    }

}


