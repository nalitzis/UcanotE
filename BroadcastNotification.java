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

/**
 * Created by ivano on 5/16/2015.
 */
public class BroadcastNotification extends BroadcastReceiver {


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Define an Intent and an action to perform with it by another application
        //manage more intents inside Broadcast http://stackoverflow.com/questions/9128103/broadcastreceiver-with-multiple-filters-or-multiple-broadcastreceivers

//if (count>9) {
//TODO FIRST  resolved? shared preferences in BroadCast to call numbersUrgent
//     SharedPreferences sharedPreferences= context.getSharedPreferences("value", context.MODE_PRIVATE);
//        String numberString=sharedPreferences.getString("key","");
//        SharedPreferences settings = context.getPreferences(getActivity().MODE_PRIVATE);
//
//        Integer pirolo =settings.getInt("variable",numbersUrgent);

        //TODO FIRST metti il backstack come main activity !
        SharedPreferences settings = context.getSharedPreferences("prova",context.MODE_PRIVATE);

        int numberUrg = settings.getInt("variable", -1);


        // int numbersUrgent;
       // String numberUrgent = Integer.toString( FragmentAsList.numbersUrgent);
        String numberUrgent = Integer.toString(numberUrg);




//TODO FIRST resolved? notification if click open urgents

        Intent intentTopNotification = new Intent(context, MainActivity.class);
        PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        //stackBuilder.addParentStack();
        stackBuilder.addNextIntent(intentTopNotification);

        // Builds a notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.reminder)
                        .setContentTitle("Tu Puoi(Ucanote)")
                        .setTicker("dumbo")
                        .setContentText("you have " + numberUrgent + " Urgent Tasks!");

        // Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(notificIntent);

        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);


        // Auto cancels the notification when clicked on in the task bar
        mBuilder.setAutoCancel(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        mNotificationManager.notify(1, mBuilder.build());


    }




}
