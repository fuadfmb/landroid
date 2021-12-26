package com.orosoft.landroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class D4_NavigationV extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dt;
    Toolbar tb;
    NavigationView nv;
    FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d4_navigation);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nv = (NavigationView) findViewById(R.id.navigation);

        tb = (Toolbar) findViewById(R.id.mytb);
        setSupportActionBar(tb);

        dt = new ActionBarDrawerToggle(this, drawerLayout, tb, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(dt);
        dt.syncState();

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new Frag1()).commit();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                int id = menuItem.getItemId();
                // we have only 3 fragments :)
                if (id == R.id.menu1 || id == R.id.menu2 || id == R.id.menu3 ){
                    tb.setTitle(menuItem.getTitle());
                }
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                switch (id){
                    case R.id.menu1:
                        mFragmentTransaction.replace(R.id.containerView, new Frag1()).commit();
                        break;
                    case R.id.menu2:
                        mFragmentTransaction.replace(R.id.containerView, new Frag2()).commit();
                        break;
                    case R.id.menu3:
                        mFragmentTransaction.replace(R.id.containerView, new Frag3()).commit();
                        break;
                    case R.id.menu4:
                        Toast.makeText(D4_NavigationV.this, "you clicked menu 4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu5:
                        Toast.makeText(D4_NavigationV.this, "you clicked menu 5", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dt.onConfigurationChanged(newConfig);
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
            intent.putExtra("file", "d4");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
