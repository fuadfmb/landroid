package com.orosoft.landroid;

import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dt;
    Toolbar tb;
    CollapsingToolbarLayout collapselayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView imglayout;
    NavigationView nv;

    static Random rand = new Random();
    final static int randomNum = rand.nextInt(5);

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);
        collapselayout = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        collapselayout.setTitleEnabled(false);



        nv = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        dt = new ActionBarDrawerToggle(this, drawerLayout, tb, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(dt);
        dt.syncState();

        imglayout = (ImageView) findViewById(R.id.imglayout);
        viewPager = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tl);

        // ask rating...
        settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = settings.edit();

        // if not rated...
        if (!settings.getBoolean("rated", false)){
            if (settings.getLong("opened", -1)==-1){
                editor.putLong("opened", 1).apply();
            }

            if(settings.getLong("opened", -1)%5==0 || settings.getLong("opened", -1)<=1){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(Html.fromHtml("<font color=\"teal\">Please Rate this App</font>"))
                        .setMessage(Html.fromHtml("Would you like to give us 5 stars on playstore?"))
                        .setCancelable(true)
                        .setPositiveButton("Yes", (dialog, which) -> {
                            rateUs();
                            editor.putBoolean("rated", true).apply();
                        })
                        .setNegativeButton("Later", (dialog, which) -> {
                            dialog.cancel();
                        })
                        .show();
            }

            editor.putLong("opened", (settings.getLong("opened", -1)+1)).apply();

        }

        attachBg();

        //cancel notifications if any
        NotificationManager notificationMgr = (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.cancel(M4_Notifications.CALL_NOTIFY_ID);
        notificationMgr.cancel(999);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // load banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.menu1:
                        // home
                        break;
                    case R.id.menu2:
                        // link to github
                        String url = "https://github.com/fuadfmb/landroid";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(Intent.createChooser(intent, "Browse with"));
                        break;
                    case R.id.mm1:
                        // share
                        shareApp();
                        break;
                    case R.id.mm3:
                        // rate
                        rateUs();
                        break;
                    case R.id.mm4:
                        // about
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;
                    case R.id.mm5:
                        finish();
                        break;
                    case R.id.privacy:

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Privacy Policy")
                                .setMessage(
                                        Html.fromHtml("<p>\n" +
                                                "This privacy policy applies to 'LAndroid - Android Examples' android Application. \n" +
                                                "you agree to this privacy policy by installing and using this Application. \n" +
                                                "please do not install or use this Application if you do not agree with this privacy policy.\n" +
                                                "</p>\n" +
                                                "<p>\n" +
                                                "This page is used to inform visitors regarding our policies with the collection, use, \n" +
                                                "and disclosure of Personal Information if anyone decided to use our Service.\n" +
                                                "If you choose to use our Service, then you agree to the collection and use of \n" +
                                                "information in relation to this policy.\n" +
                                                "</p>\n" +
                                                "\n" +
                                                "<h2>Information Collection and Use</h2>\n" +
                                                "<p>\n" +
                                                "we do not collect or use any personal data from users.\n" +
                                                "the Application does not send any information to Orosoft Technologies without users knowledge.\n" +
                                                "if you send us feedback or question via email, \n" +
                                                "your email address is visible to us and we may contact you directly through that email if necessary. \n" +
                                                "</p>\n" +
                                                "\n" +
                                                "<p>\n" +
                                                "we do not ask any personal information such as:- your name, country, place of birth,\n" +
                                                "email address, photos, phone number and etc from our customers.\n" +
                                                "\n" +
                                                "\n" +
                                                "</p>\n" +
                                                "\n" +
                                                "\n" +
                                                "<h2>Children's Privacy</h2>\n" +
                                                "<p>\n" +
                                                "These Services do not address anyone under \n" +
                                                "the age of 13. We do not knowingly collect \n" +
                                                "personally identifiable information from children \n" +
                                                "under 13.\n" +
                                                "</p>\n" +
                                                "\n" +
                                                "<h2>Changes to This Privacy Policy</h2>\n" +
                                                "<p>\n" +
                                                "We may update our Privacy Policy from time to time. \n" +
                                                "Thus, you are advised to review <a href=\"https://orosoftblog.blogspot.com/p/seerlugaa-privacy-policy.html\">this page</a> periodically\n" +
                                                "for any changes. We will notify you of any changes by \n" +
                                                "posting the new Privacy Policy on this page. These \n" +
                                                "changes are effective immediately after they are posted \n" +
                                                "on this page.\n" +
                                                "</p>\n" +
                                                "\n" +
                                                "<h2>Links to Other Sites</h2>\n" +
                                                "<p>\n" +
                                                "This Service may contain links to other sites. \n" +
                                                "If you click on a third-party link, you will \n" +
                                                "be directed to that site. Note that these \n" +
                                                "external sites are not operated by us. \n" +
                                                "Therefore, we strongly advise you to review \n" +
                                                "the Privacy Policy of these websites. \n" +
                                                "We have no control over and assume no \n" +
                                                "responsibility for the content, privacy \n" +
                                                "policies, or practices of any third-party sites or services.\n" +
                                                "\n" +
                                                "\n" +
                                                "<h2>Contact Us</h2>\n" +
                                                "<p>\n" +
                                                "If you have any questions or suggestions about our \n" +
                                                "Privacy Policy, do not hesitate to contact us.<br>\n" +
                                                "Email : fuadmoh9@gmail.com\n" +
                                                "\n" +
                                                "</p>")
                                )
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setCancelable(true)
                                .create()
                                .show();

                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.closealert, null);
        builder.setView(view);
        final AlertDialog d = builder.create();
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.show();

        Button yes = view.findViewById(R.id.btn_yes);
        Button cancel = view.findViewById(R.id.btn_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.cancel();
            }
        });

    }

    public void rateUs(){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        }
    }
    private void attachBg() {
        switch (randomNum) {
            case 0:
                imglayout.setBackgroundResource(R.drawable.img1);
                break;
            case 1:
                imglayout.setBackgroundResource(R.drawable.img2);
                break;
            case 2:
                imglayout.setBackgroundResource(R.drawable.img3);
                break;
            case 3:
                imglayout.setBackgroundResource(R.drawable.img4);
                break;
            case 4:
                imglayout.setBackgroundResource(R.drawable.img5);
                break;
            case 5:
                imglayout.setBackgroundResource(R.drawable.img6);
                break;
        }
    }

    private void shareApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Download this app from :\nhttps://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName() );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "share via :"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Cat_Basics();
                case 1:
                    return new Cat_More();
                case 2:
                    return new Cat_Design();
                case 3:
                    return new Cat_Intents();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Basics";
                case 1:
                    return "Plus";
                case 2:
                    return "Design";
                case 3:
                    return "More";
            }
            return null;
        }

    }

    public void openExampleBasics(View view) {


        switch (view.getId()) {
            case R.id.b1:
                startActivity(new Intent(MainActivity.this, B1_Toast.class));
                break;
            case R.id.b2:
                startActivity(new Intent(MainActivity.this, B2_Button.class));
                break;
            case R.id.b3:
                startActivity(new Intent(MainActivity.this, B3_Switch.class));
                break;
            case R.id.b4:
                startActivity(new Intent(MainActivity.this, B4_CheckBox.class));
                break;
            case R.id.b5:
                startActivity(new Intent(MainActivity.this, B5_ProgressBar.class));
                break;
            case R.id.b6:
                startActivity(new Intent(MainActivity.this, B6_ToggleButton.class));
                break;
            case R.id.b7:
                startActivity(new Intent(MainActivity.this, B7_Seekbar.class));
                break;
            case R.id.b8:
                startActivity(new Intent(MainActivity.this, B8_Spinner.class));
                break;
            case R.id.b9:
                startActivity(new Intent(MainActivity.this, B9_WebView.class));
                break;
            case R.id.b10:
                startActivity(new Intent(MainActivity.this, B10_Radiobutton.class));
                break;
            case R.id.b11:
                startActivity(new Intent(MainActivity.this, B11_Menus.class));
                break;
        }
        //if (vibrator.hasVibrator())  vibrator.vibrate(50);

    }

    public void openExampleMore(View view) {
        switch (view.getId()) {
            case R.id.m1:
                startActivity(new Intent(MainActivity.this, M1_AlertDialog.class));
                break;
            case R.id.m2:
                startActivity(new Intent(MainActivity.this, M2_ListView.class));
                break;
            case R.id.m3:
                startActivity(new Intent(MainActivity.this, M3_AutoCompleteTv.class));
                break;
            case R.id.m4:
                startActivity(new Intent(MainActivity.this, M4_Notifications.class));
                break;
            case R.id.m5:
                startActivity(new Intent(MainActivity.this, M5_SwipeToRefresh.class));
                break;
            case R.id.m6:
                startActivity(new Intent(MainActivity.this, M6_Animation.class));
                break;
            case R.id.m7:
                startActivity(new Intent(MainActivity.this, M7_CustomFonts.class));
                break;
            case R.id.m8:
                startActivity(new Intent(MainActivity.this, M8_TextToSpeech.class));
                break;
            case R.id.m9:
                startActivity(new Intent(MainActivity.this, M9_CountDownTimer.class));
                break;
            case R.id.m10:
                startActivity(new Intent(MainActivity.this, M10_CheckConnection.class));
                break;
        }
    }

    public void openExampleDesign(View view) {
        switch (view.getId()) {
            case R.id.d1:
                startActivity(new Intent(MainActivity.this, D1_Toolbar.class));
                break;
            case R.id.d2:
                startActivity(new Intent(MainActivity.this, D2_snackbar.class));
                break;
            case R.id.d3:
                startActivity(new Intent(MainActivity.this, D3_TabLayout.class));
                break;
            case R.id.d4:
                startActivity(new Intent(MainActivity.this, D4_NavigationV.class));
                break;
            case R.id.d5:
                startActivity(new Intent(MainActivity.this, D5_TextInputLayout.class));
                break;
            case R.id.d6:
                startActivity(new Intent(MainActivity.this, D6_CardView.class));
                break;
            case R.id.d7:
                startActivity(new Intent(MainActivity.this, D7_BottomSheet.class));
                break;
            case R.id.d8:
                startActivity(new Intent(MainActivity.this, D8_RecyclerView.class));
                break;
            case R.id.d9:
                startActivity(new Intent(MainActivity.this, D9_BottomNav.class));
                break;
            case R.id.d10:
                startActivity(new Intent(MainActivity.this, D10_FAB.class));
                break;
        }
    }

    public void openExampleIntents(View view) {
        switch (view.getId()) {
            case R.id.i1:
                startActivity(new Intent(MainActivity.this, I1_MakeCall.class));
                break;
            case R.id.i2:
                startActivity(new Intent(MainActivity.this, I2_SMS.class));
                break;
            case R.id.i3:
                startActivity(new Intent(MainActivity.this, I3_Email.class));
                break;
            case R.id.i4:
                startActivity(new Intent(MainActivity.this, I4_ViewPager.class));
                break;
            case R.id.i7:
                startActivity(new Intent(MainActivity.this, I7_SplashScreen.class));
                break;
            case R.id.i8:
                startActivity(new Intent(MainActivity.this, I8_Sqlite.class));
                break;
            case R.id.i10:
                startActivity(new Intent(MainActivity.this, I10_ViewFlipper.class));
                break;
            case R.id.i11:
                startActivity(new Intent(MainActivity.this, I11_MoreIntents.class));
                break;
            case R.id.i15:
                startActivity(new Intent(MainActivity.this, MusicPlayer.class));
                break;
            case R.id.i16:
                startActivity(new Intent(MainActivity.this, VideoPlayer.class));
                break;

        }
    }

}




