package com.project.stickysoft.customalarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Sticky on 4/18/2017.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, "Alarm Time Complete", "This is an alarm notification reminder", "Custom Alarm Notification");

    }

    public void createNotification(Context context, String message, String messageText, String messageAlert){

        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0, new Intent(context, AlarmInformation.class), 0);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle(message)
                .setTicker(messageAlert)
                .setContentText(messageText);

        notificationBuilder.setContentIntent(notificationIntent);
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        notificationBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
