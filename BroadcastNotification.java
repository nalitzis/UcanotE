package ivano.android.com.ucanote;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by ivano on 5/16/2015.
 */
public class BroadcastNotification extends BroadcastReceiver {
    public static final String ACTION = "ivano.android.com.intent.ACTION";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {


//TODO TEMP



        NotificationCompat.Builder mBuilder, rowAlarm = null;

        if (intent.getAction().equals(ACTION)) {


            PendingIntent notifyRowIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);

            rowAlarm = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.share2)
                    .setContentTitle("Attention! U can:")
                    .setTicker("boh2")
                    .setContentText(FragmentAsList.description);


            rowAlarm.setContentIntent(notifyRowIntent);
            rowAlarm.setDefaults(Notification.DEFAULT_SOUND);
            rowAlarm.setAutoCancel(true);
            NotificationManager m2NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            m2NotificationManager.notify(1, rowAlarm.build());


        } else {
            SharedPreferences settings = context.getSharedPreferences("prova", context.MODE_PRIVATE);

            int numberUrg = settings.getInt("variable", -1);
Log.d("BroadcastNotification", "onReceive (line 56): numberUrgent"+numberUrg);

            Intent intentTopNotification = new Intent(context, MainActivity.class);
            PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

            stackBuilder.addNextIntent(intentTopNotification);


            if (numberUrg > 5) {
                mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.reminder)
                                .setContentTitle("U can")
                                .setTicker("dumbo")
                                .setContentText("Ehy " + numberUrg + " Urgent Tasks! It is a bit too much!!");

                mBuilder.setContentIntent(notificIntent);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND);


                mBuilder.setAutoCancel(true);

                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());
            } else if (numberUrg > 8) {

                mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.reminder)
                                .setContentTitle("Tu Puoi(Ucanote)")
                                .setTicker("dumbo")
                                .setContentText("Ehy maybe now is time to focus buddy! You have " + numberUrg + " Urgent Tasks! You can do it!");
                mBuilder.setContentIntent(notificIntent);


                mBuilder.setDefaults(Notification.DEFAULT_SOUND);


                mBuilder.setAutoCancel(true);

                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());

            }
           scheduleAlarms(context);

        }

    }


static void scheduleAlarms(Context context) {
     AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent(context, IntentServiceClass.class);
    PendingIntent pendingIntent = PendingIntent.getService(context, 0, i, 0);
    mgr.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+5000,5000,pendingIntent);

}
}
