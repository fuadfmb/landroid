package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class B11_Menus extends AppCompatActivity {

    Button menu_options, menu_context, menu_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b11_menus);

        menu_options = findViewById(R.id.menu_options);
        menu_context = findViewById(R.id.menu_context);
        menu_popup   = findViewById(R.id.menu_popup);

        // for menu context
        registerForContextMenu(menu_context);
        // for popup menu
        menu_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu pop = new PopupMenu(B11_Menus.this, menu_popup);
                pop.getMenuInflater().inflate(R.menu.popupmenu, pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String message = "";
                        if (item.getItemId() == R.id.item_11){
                            message = "menu 1";
                        }
                        else if (item.getItemId() == R.id.item_22){
                            message = "menu 2";
                        }
                        Toast.makeText(B11_Menus.this, message, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                pop.show();
            }
        });
        // for options menu
        menu_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(B11_Menus.this, "press 3 dots on the toolbar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // for options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // for options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_1){
            Toast.makeText(this, "You clicked item 1", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.item_2){
            Toast.makeText(this, "You clicked item 2", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.show_source) {
            Intent intent = new Intent(getApplicationContext(), Viewer.class);
            intent.putExtra("file", "b11");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    // for context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.ic_double_arrow);
        menu.setHeaderTitle("My Context Menu");
        menu.add(0, v.getId(),0, "Delete");
        menu.add(0, v.getId(),0, "Edit");
    }

    // for context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Delete")){
            Toast.makeText(this, "You clicked delete!", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("Edit")){
            Toast.makeText(this, "You clicked Edit", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }


}


