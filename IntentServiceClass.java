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
     * @param name Used to name the worker thread, important only for debugging.
     */
    public static final String PARAM_IN_MSG = "imsg";
    public IntentServiceClass() {
        super("IntentServiceClass");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        String msg =intent.getStringExtra("Pom");
        String rsltText=msg;
        Log.d("ivano.android.com.ucanote.IntentServiceClass", "onHandleIntent (line 25): ");


        Intent broadInt = new Intent(BroadcastNotification.ACTION);

        broadInt.putExtra("PomOut", rsltText+"  in Uscita!");
sendBroadcast(broadInt);

    }

}
