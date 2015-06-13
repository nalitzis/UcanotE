package ivano.android.com.ucanote;

import android.app.IntentService;
import android.content.Intent;
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

//    Intent myIntent = new Intent(BroadcastNotification.ACTION);
//    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//    PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
//
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 8);
//
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);
//
//
//
//
//sendBroadcast(myIntent);



    }

}
