package com.orosoft.landroid;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class M4_Notifications extends AppCompatActivity {

    Button notify_simple, notify_headsup, notify3, notify4;
    public static int CALL_NOTIFY_ID = 10001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4_notifications);

        notify_simple = (Button) findViewById(R.id.notify_simple);
        notify_headsup = (Button) findViewById(R.id.notify2);
        notify3 = (Button) findViewById(R.id.notify3);
        notify4 = (Button) findViewById(R.id.notify4);



        //cancel if there are any notifications..
        NotificationManager notificationMgr = (NotificationManager) M4_Notifications.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.cancel(M4_Notifications.CALL_NOTIFY_ID);
        notificationMgr.cancel(999);


        notify_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int notificationID = 10101;

                NotificationCompat.Builder builder = new NotificationCompat.Builder(M4_Notifications.this)
                        .setSmallIcon(R.drawable.info) // notification icon
                        .setContentTitle("Simple notification")
                        .setContentText("Description goes here...")
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                        .setWhen(System.currentTimeMillis());

                Intent intent = new Intent(M4_Notifications.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(M4_Notifications.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);

                NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(notificationID, builder.build());

            }
        });

        notify_headsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationID = 10102;
                // this is dynamic, device independent notification
                Intent i = new Intent(M4_Notifications.this, M4_Notifications.class);
                i.setAction("com.example.myapp.notification");
                i.putExtra("some_extra", "testValue");
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent notificationIntent = PendingIntent.getActivity(M4_Notifications.this, 999, i,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(M4_Notifications.this.getApplicationContext());
                builder.setContentIntent(notificationIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(M4_Notifications.this.getResources(), R.drawable.person));
                builder.setSmallIcon(R.drawable.ic_comment);
                builder.setContentTitle("John Doe");
                builder.setContentText("Hello");
                builder.setTicker("1");

                //for button click events
                Intent intent = new Intent(M4_Notifications.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pIntent = PendingIntent.getActivity(M4_Notifications.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

                builder.addAction(R.drawable.ic_call, "VOICE", pIntent);
                builder.addAction(R.drawable.ic_videos, "VIDEO", pIntent);
                builder.addAction(R.drawable.ic_comment, "MESSAGE", pIntent);

                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                if (Build.VERSION.SDK_INT >= 21) builder.setVibrate(new long[0]);
                NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(999, builder.build());
            }
        });

        notify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a custom notification with a very very very very very very very very very very long text. " +
                        "This is a custom notification with a very very very very very very very very very very long text";
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.error);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(M4_Notifications.this);
                builder.setContentTitle("Title").setContentText(message)
                        .setSmallIcon( R.drawable.error )
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setWhen(System.currentTimeMillis())
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                Notification notification = builder.build();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(M4_Notifications.this);
                notificationManager.notify(101, notification);
            }
        });

        notify4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cancel older notification with same id,
                NotificationManager notificationMgr = (NotificationManager) M4_Notifications.this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationMgr.cancel(CALL_NOTIFY_ID);// any constant value
                // Notification builder
                NotificationCompat.Builder builder = new NotificationCompat.Builder(M4_Notifications.this);
                builder.setContentTitle("This is a title")
                        .setContentText("Description goes here...")
                        .setSmallIcon(R.drawable.error)
                        .setUsesChronometer(true)
                        .setDefaults(Notification.DEFAULT_LIGHTS)
                        .setAutoCancel(true)
                        .setOngoing(true);
                // dismiss notification after one click
                builder.setAutoCancel(true);
                // Add action button in the notification
                Intent intent = new Intent(M4_Notifications.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pIntent = PendingIntent.getActivity(M4_Notifications.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                builder.addAction(R.drawable.error, "Cancel", pIntent);

                // Notify using notificationMgr
                Notification finalNotification = builder.build();
                notificationMgr.notify(CALL_NOTIFY_ID, finalNotification);
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
            intent.putExtra("file", "m4");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}