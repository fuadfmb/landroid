package com.orosoft.landroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class I11_MoreIntents extends AppCompatActivity {

    Button shareintent, urlintent, openmap, shareimgintent, filechooser;
    public static int REQUEST_CODE = 101; //any random  number
    ImageView chosenimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i11_moreintents);

        shareintent = (Button) findViewById(R.id.shareintent);
        urlintent = (Button) findViewById(R.id.urlintent);
        openmap = (Button) findViewById(R.id.openmap);
        shareimgintent = (Button) findViewById(R.id.shareimg);
        filechooser = (Button) findViewById(R.id.filechooser);
        chosenimage = (ImageView) findViewById(R.id.chosenimage);

        shareintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Example Description :\n" +
                        "This is App ID :\n" + getApplicationContext().getPackageName() );
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "share via :"));

            }
        });

        urlintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( "http://www.google.com" ));
                Intent urlintent = Intent.createChooser(intent, "Browse with");
                startActivity( urlintent );

            }
        });

        openmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", 8.9919845,38.7731445);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        });

        shareimgintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                String packageName = getApplicationContext().getPackageName();
                Uri myimage = Uri.parse("android.resource://" + packageName + "/drawable/img1");
                shareIntent.putExtra(Intent.EXTRA_STREAM, myimage);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "Share to"));

            }
        });

        filechooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setType("image/*");
                startActivityForResult(gallery, REQUEST_CODE);

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
            intent.putExtra("file", "i11");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imguri = null;

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){

            imguri = data.getData();
            chosenimage.setImageURI(imguri);

            /////////////////////////
            ///    CHECK IMAGE SIZE
            ///
            ///    Cursor returnCursor = getContentResolver().query(imguri, null, null, null, null);
            ///    //int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            ///    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            ///    returnCursor.moveToFirst();
            ///    boolean imagesize = Long.toString(returnCursor.getLong(sizeIndex)).length() <= 6;
            ///    if (imagesize){
            ///        chosenimage.setImageURI(imguri);
            ///        imgSelected = true;
            ///    }
            ///    else{
            ///        Toast.makeText(getApplicationContext(), "please try to minimise the size of the image..." , Toast.LENGTH_LONG).show();
            ///        imgSelected = false;
            ///        Uri uri = Uri.parse("android.resource://com.orosoft.astunews/drawable/selectimage");
            ///        selectimg.setImageURI(uri);
            ///        imguri = null;
            ///    }
            ///    returnCursor.close();
            /////////////////////////

        }
    }


}