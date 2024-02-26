package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    private final String CHANNEL_ID="personal_notification";
    private final int NOTIFICATION_ID=001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //R means resource folder
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vew){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vew){
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    public void DisplayNotifications(View v){
//        createNotificationChannel();
        NotificationCompat.Builder builder;
        builder =new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Sample Notification")
                .setContentText("Welcome to My Application")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        try{
            notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());}
        catch ( SecurityException e){

        }

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Personal Notification";
            String description ="Include all personal notification";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}