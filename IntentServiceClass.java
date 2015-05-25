package ivano.android.com.ucanote;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;

/**
 * Created by ivano on 5/24/2015.
 */
public class IntentServiceClass extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceClass(String name) {
        super("IntentServiceClass");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg = intent.getStringExtra("ciaoooo");
        SystemClock.sleep(30000); // 30 seconds
        String resultTxt = msg + " "
                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
    }
}
