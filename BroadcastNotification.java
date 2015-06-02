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
import android.widget.Toast;

/**
 * Created by ivano on 5/16/2015.
 */
public class BroadcastNotification extends BroadcastReceiver {
    public static final String ACTION = "ivano.android.com.intent.ACTION";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {

     if(intent.getAction().equals(ACTION)) {
         Toast.makeText(context, "text", Toast.LENGTH_LONG);
         Log.d("ivano.android.com.ucanote.BroadcastNotification", "onReceive (line 29): passed");
     }else {
         // Define an Intent and an action to perform with it by another application
         //manage more intents inside Broadcast http://stackoverflow.com/questions/9128103/broadcastreceiver-with-multiple-filters-or-multiple-broadcastreceivers

//if (count>9) [
//TODO FIRST  resolved? shared preferences in BroadCast to call numbersUrgent,
// also put the condition iF MAJOR THAN 5, AND MORE THAN 8


         //TODO  metti il backstack come main activity !
         SharedPreferences settings = context.getSharedPreferences("prova", context.MODE_PRIVATE);

         int numberUrg = settings.getInt("variable", -1);
         Log.d("ivano.android.com.ucanote.BroadcastNotification", "onReceive (line 39): numberUrg" + numberUrg);


         // int numbersUrgent;
         // String numberUrgent = Integer.toString( FragmentAsList.numbersUrgent);
        // String numberUrgent = Integer.toString(numberUrg);

//TODO ASK the line below are duplicated this is not efficient, because repeated in the next
         //if condition
         Intent intentTopNotification = new Intent(context, MainActivity.class);
         PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                 new Intent(context, MainActivity.class), 0);

         TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

         //stackBuilder.addParentStack();
         stackBuilder.addNextIntent(intentTopNotification);


         NotificationCompat.Builder mBuilder = null;
         // Builds a notification
         if (numberUrg > 5) {
             mBuilder =
                     new NotificationCompat.Builder(context)
                             .setSmallIcon(R.drawable.reminder)
                             .setContentTitle("Tu Puoi(Ucanote)")
                             .setTicker("dumbo")
                             .setContentText("Ehy " + numberUrg + " Urgent Tasks! It is a bit too much!!");
                             //.setContentText("Ehy " + numberUrgent + " Urgent Tasks! It is a bit too much!!");

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
         } else if (numberUrg > 8) {

             mBuilder =
                     new NotificationCompat.Builder(context)
                             .setSmallIcon(R.drawable.reminder)
                             .setContentTitle("Tu Puoi(Ucanote)")
                             .setTicker("dumbo")
                             .setContentText("Ehy maybe now is time to focus buddy! You have " + numberUrg + " Urgent Tasks! You can do it!");
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

    }



}
