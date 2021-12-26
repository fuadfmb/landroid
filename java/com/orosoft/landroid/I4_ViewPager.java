package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class I4_ViewPager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i4_viewpager);

        ViewPager pager = (ViewPager) findViewById(R.id.photos_viewpager);
        PagerAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);
    }

    public void showSource(View v){
        Intent intent = new Intent(getApplicationContext(), Viewer.class);
        intent.putExtra("file", "i4");
        startActivity(intent);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Frag1();
                case 1:
                    return new Frag2();
                case 2:
                    return new Frag3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

}