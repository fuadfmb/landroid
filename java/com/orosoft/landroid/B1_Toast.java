package com.orosoft.landroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class B1_Toast extends AppCompatActivity {

    Button normaltoast, customtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_toast);

        normaltoast = (Button) findViewById(R.id.normaltoast);
        customtoast = (Button) findViewById(R.id.customtoast);

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        normaltoast.startAnimation(anim1);

        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        customtoast.startAnimation(anim2);

        normaltoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalToast("Hello, this is me. a Toast!");
            }

        });

        customtoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Message sent!", "Message sent successfully !", Toast.LENGTH_LONG);
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
            intent.putExtra("file", "b1");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void showCustomToast(String msgTitle, String msgDesc, int length) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_container));
        // Set the title and description TextViews from our custom layout
        TextView title = (TextView) layout.findViewById(R.id.title);
        title.setText( msgTitle );
        TextView description = (TextView) layout.findViewById(R.id.description);
        description.setText( msgDesc );
        // Create and show the Toast object
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 30);
        toast.setDuration( length );
        toast.setView(layout);
        toast.show();
    }

    public void showNormalToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
