package ivano.android.com.ucanote;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by ivano on 5/24/2015.
 */
public class IntentServiceClass extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    public IntentServiceClass() {
        super("IntentServiceClass");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
Log.d(getClass().getSimpleName(), "I ran!");
            NotificationCompat.Builder rowAlarm = null;

        PendingIntent notifyRowIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        rowAlarm = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.share2)
                .setContentTitle("Attention! U can:")
                .setTicker("boh2")
                .setContentText(FragmentAsList.description);


        rowAlarm.setContentIntent(notifyRowIntent);
        rowAlarm.setDefaults(Notification.DEFAULT_SOUND);
        rowAlarm.setAutoCancel(true);
        NotificationManager m2NotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        m2NotificationManager.notify(1, rowAlarm.build());










    }

}
