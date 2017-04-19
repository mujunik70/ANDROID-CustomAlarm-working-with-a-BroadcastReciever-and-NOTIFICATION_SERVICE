package com.project.stickysoft.customalarm;



import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText alarmTimePeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void createAlarm(View view){

        alarmTimePeriod = (EditText)findViewById(R.id.alarmTime);
        int time = Integer.parseInt(alarmTimePeriod.getText().toString());


        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);
//        //Creating a notification on click-------Original Copy----------
//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new
//                NotificationCompat.Builder(this)
//                .setContentTitle("Alarm Notification")
//                .setContentText("You set an alarm " +time+ " seconds ago")
//                .setTicker("New Alarm Notification")
//                .setSmallIcon(R.drawable.alarm);


        //Duplicate-----Not Applicable
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//        //-----------------
//
//        Intent notificationInfo = new Intent(this, AlarmInformation.class);
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
//        taskStackBuilder.addParentStack(AlarmInformation.class);
//        taskStackBuilder.addNextIntent(notificationInfo);
//
//        PendingIntent pendingNotificationIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
//        notificationBuilder.setContentIntent(pendingNotificationIntent);
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(notificationId, notificationBuilder.build());
//        isNotificationActive = true;
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time*1000), pendingIntent);

        Long showAlertTime = new GregorianCalendar().getTimeInMillis() + time*1000;
        Intent alarmIntent = new Intent(this, AlarmBroadcastReceiver.class);

        alarmManager.set(AlarmManager.RTC_WAKEUP, showAlertTime,
                PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT));

        alarmTimePeriod.setText("");

        Toast.makeText(this, "Alarm for " + time + " has been set", Toast.LENGTH_LONG).show();


    }


}
