package ivano.android.com.ucanote;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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





        NotificationCompat.Builder mBuilder;

        if (intent.getAction().equals(ACTION)) {
            //future feature at the moment can be ignored is here to not write
            // again the nested if function, as in future will manage the update row function
            Log.d(getClass().getSimpleName(), "passed!");


        } else {
            SharedPreferences settings = context.getSharedPreferences("prova", context.MODE_PRIVATE);

            int numberUrg = settings.getInt("variable", -1);

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



        }

    }



}
